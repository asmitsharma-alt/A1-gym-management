package com.a1gym.manager.ui.members

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.a1gym.manager.data.AppDatabase
import com.a1gym.manager.data.entity.Member
import com.a1gym.manager.databinding.FragmentMemberProfileBinding
import com.a1gym.manager.repository.GymRepository
import com.a1gym.manager.viewmodel.GymViewModel
import com.a1gym.manager.viewmodel.GymViewModelFactory
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import android.app.Dialog
import android.widget.ArrayAdapter
import android.view.Window
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import com.a1gym.manager.data.entity.Plan
import com.a1gym.manager.data.entity.Payment
import com.a1gym.manager.databinding.DialogRenewMemberBinding
import com.bumptech.glide.Glide

class MemberProfileFragment : Fragment() {

    private var _binding: FragmentMemberProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GymViewModel
    private var memberId: Long = -1L
    private var currentMember: Member? = null
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemberProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        memberId = arguments?.getLong("memberId") ?: -1L
        if (memberId == -1L) {
            Toast.makeText(requireContext(), "Error loading member profile", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
            return
        }

        val database = AppDatabase.getDatabase(requireContext(), lifecycleScope)
        val repository = GymRepository(database.memberDao(), database.planDao(), database.paymentDao())
        val factory = GymViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[GymViewModel::class.java]

        loadMember()
        setupListeners()
    }

    private fun loadMember() {
        viewLifecycleOwner.lifecycleScope.launch {
            val database = AppDatabase.getDatabase(requireContext(), lifecycleScope)
            val member = database.memberDao().getMemberById(memberId)
            
            if (member != null) {
                currentMember = member
                populateUI(member)
            }
        }
    }

    private fun populateUI(member: Member) {
        binding.tvMemberName.text = member.name
        binding.tvPhone.text = member.phone
        binding.tvAddress.text = member.address.ifEmpty { "N/A" }
        binding.tvDates.text = "${dateFormat.format(Date(member.startDate))} - ${dateFormat.format(Date(member.endDate))}"

        if (!member.photoUri.isNullOrEmpty()) {
            binding.ivMemberPhoto.setPadding(0, 0, 0, 0)
            Glide.with(requireContext())
                .load(member.photoUri)
                .circleCrop()
                .placeholder(android.R.drawable.ic_menu_camera)
                .into(binding.ivMemberPhoto)
        } else {
            binding.ivMemberPhoto.setImageResource(android.R.drawable.ic_menu_camera)
        }
        binding.tvAmountPaid.text = "Amount Paid: ₹${member.amount} via ${member.paymentMethod}"
        
        binding.tvStatus.text = member.status
        val statusColor = when (member.status) {
            "Active" -> Color.parseColor("#4CAF50")
            "Expired" -> Color.parseColor("#EF4444")
            "Expiring Soon" -> Color.parseColor("#F59E0B")
            else -> Color.GRAY
        }
        
        val background = binding.tvStatus.background as? GradientDrawable
        if (background != null) {
            background.setColor(statusColor)
        } else {
            val newBg = GradientDrawable()
            newBg.setColor(statusColor)
            newBg.cornerRadius = 40f
            binding.tvStatus.background = newBg
        }
        
        binding.tvPlanNames.text = "Plan Details (ID: ${member.planId})"
    }

    private fun setupListeners() {
        binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Delete Member")
                .setMessage("Are you sure you want to delete this member? All data including payments will be lost.")
                .setPositiveButton("Delete") { _, _ ->
                    currentMember?.let {
                        viewModel.deleteMember(it)
                        Toast.makeText(requireContext(), "Member deleted", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
        
        binding.btnRenew.setOnClickListener {
            showRenewDialog()
        }

        binding.btnCallMember.setOnClickListener {
            currentMember?.let { member ->
                if (member.phone.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:${member.phone}")
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "No phone number available", Toast.LENGTH_SHORT).show()
                }
            }
        }
        
        binding.btnGenerateInvoice.setOnClickListener {
            currentMember?.let { member ->
                val options = arrayOf("Share via WhatsApp", "Export as PDF")
                AlertDialog.Builder(requireContext())
                    .setTitle("Generate Invoice")
                    .setItems(options) { _, which ->
                        when(which) {
                            0 -> com.a1gym.manager.InvoiceHelper.shareViaWhatsApp(requireContext(), member)
                            1 -> com.a1gym.manager.InvoiceHelper.generateAndSharePdf(requireContext(), member)
                        }
                    }
                    .show()
            }
        }
    }

    private fun showRenewDialog() {
        val member = currentMember ?: return
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogBinding = DialogRenewMemberBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // Set Start Date to current End Date if active, or today if expired
        val now = System.currentTimeMillis()
        var newStartDate = member.endDate
        if (newStartDate < now) {
            newStartDate = now
        }
        
        var selectedPlan: Plan? = null
        
        fun updateEndDateField() {
            selectedPlan?.let { plan ->
                val cal = Calendar.getInstance()
                cal.timeInMillis = newStartDate
                cal.add(Calendar.DAY_OF_YEAR, plan.durationDays)
                dialogBinding.etEndDateDialog.setText(dateFormat.format(cal.time))
            }
        }
        
        dialogBinding.etStartDateDialog.setText(dateFormat.format(Date(newStartDate)))
        dialogBinding.etStartDateDialog.setOnClickListener {
            val cal = Calendar.getInstance()
            cal.timeInMillis = newStartDate
            DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val newDate = Calendar.getInstance()
                    newDate.set(year, month, dayOfMonth)
                    newStartDate = newDate.timeInMillis
                    dialogBinding.etStartDateDialog.setText(dateFormat.format(newDate.time))
                    updateEndDateField()
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        
        // Setup Payment Methods
        val paymentMethods = arrayOf("Cash", "UPI", "Online")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, paymentMethods)
        dialogBinding.actvPaymentMethodDialog.setAdapter(adapter)

        // Load Plans
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allPlans.collect { plans ->
                val planNames = plans.map { it.planName }
                val planAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, planNames)
                dialogBinding.actvPlanDialog.setAdapter(planAdapter)
                
                dialogBinding.actvPlanDialog.setOnItemClickListener { _, _, position, _ ->
                    selectedPlan = plans[position]
                    updateEndDateField()
                    dialogBinding.etAmountDialog.setText(selectedPlan!!.price.toString())
                }
            }
        }
        
        dialogBinding.btnCancelDialog.setOnClickListener {
            dialog.dismiss()
        }
        
        dialogBinding.btnRenewDialog.setOnClickListener {
            val amountStr = dialogBinding.etAmountDialog.text.toString()
            val paymentMethod = dialogBinding.actvPaymentMethodDialog.text.toString()
            
            if (selectedPlan == null || amountStr.isEmpty() || paymentMethod.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val amount = amountStr.toDoubleOrNull() ?: 0.0
            val cal = Calendar.getInstance()
            cal.timeInMillis = newStartDate
            cal.add(Calendar.DAY_OF_YEAR, selectedPlan!!.durationDays)
            val newEndDate = cal.timeInMillis
            
            val updatedMember = member.copy(
                planId = selectedPlan!!.planId,
                startDate = newStartDate,
                endDate = newEndDate,
                amount = amount,
                paymentMethod = paymentMethod,
                status = "Active"
            )
            
            val invoiceNumber = "INV-${System.currentTimeMillis()}"
            val payment = Payment(
                memberId = member.id,
                amount = amount,
                paymentDate = System.currentTimeMillis(),
                method = paymentMethod,
                invoiceNumber = invoiceNumber
            )
            
            viewModel.updateMember(updatedMember)
            viewModel.insertPayment(payment)
            
            Toast.makeText(requireContext(), "Membership Renewed successfully!", Toast.LENGTH_SHORT).show()
            
            dialog.dismiss()
            
            this.currentMember = updatedMember
            populateUI(updatedMember)
        }
        
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

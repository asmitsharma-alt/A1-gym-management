package com.a1gym.manager.ui.members

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.a1gym.manager.data.AppDatabase
import com.a1gym.manager.data.entity.Member
import com.a1gym.manager.data.entity.Payment
import com.a1gym.manager.data.entity.Plan
import com.a1gym.manager.databinding.FragmentAddMemberBinding
import com.a1gym.manager.repository.GymRepository
import com.a1gym.manager.viewmodel.GymViewModel
import com.a1gym.manager.viewmodel.GymViewModelFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import androidx.activity.result.contract.ActivityResultContracts
import android.graphics.Bitmap

class AddMemberFragment : Fragment() {

    private var _binding: FragmentAddMemberBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GymViewModel
    
    private var selectedStartDate: Long = System.currentTimeMillis()
    private var selectedPlan: Plan? = null
    private var plansList: List<Plan> = emptyList()
    private var photoUriString: String? = null
    
    private val takePhotoLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        if (bitmap != null) {
            binding.ivMemberPhoto.setImageBitmap(bitmap)
            try {
                val filename = "member_${System.currentTimeMillis()}.jpg"
                requireContext().openFileOutput(filename, android.content.Context.MODE_PRIVATE).use { stream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
                }
                photoUriString = requireContext().getFileStreamPath(filename).absolutePath
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Failed to save photo", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMemberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val database = AppDatabase.getDatabase(requireContext(), lifecycleScope)
        val repository = GymRepository(database.memberDao(), database.planDao(), database.paymentDao())
        val factory = GymViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[GymViewModel::class.java]

        setupUI()
        loadPlans()
    }

    private fun setupUI() {
        // Set default start date to today
        binding.etStartDate.setText(dateFormat.format(Date(selectedStartDate)))

        binding.etStartDate.setOnClickListener {
            showDatePicker()
        }

        binding.ivMemberPhoto.setOnClickListener {
            takePhotoLauncher.launch(null)
        }
        
        binding.tvAddPhoto.setOnClickListener {
            takePhotoLauncher.launch(null)
        }

        val paymentMethods = arrayOf("Cash", "UPI", "Online")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, paymentMethods)
        binding.actvPaymentMethod.setAdapter(adapter)

        binding.actvPlan.setOnItemClickListener { _, _, position, _ ->
            selectedPlan = plansList[position]
            updateEndDate()
            binding.etAmount.setText(selectedPlan?.price.toString())
        }
        
        binding.btnSaveMember.setOnClickListener {
            saveMember(generateInvoice = false)
        }
        
        binding.btnSaveAndInvoice.setOnClickListener {
            saveMember(generateInvoice = true)
        }
    }

    private fun loadPlans() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allPlans.collect { plans ->
                plansList = plans
                val planNames = plansList.map { it.planName }
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, planNames)
                binding.actvPlan.setAdapter(adapter)
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selectedStartDate
        
        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate.set(year, month, dayOfMonth)
                selectedStartDate = newDate.timeInMillis
                binding.etStartDate.setText(dateFormat.format(newDate.time))
                updateEndDate()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateEndDate() {
        selectedPlan?.let { plan ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectedStartDate
            calendar.add(Calendar.DAY_OF_YEAR, plan.durationDays)
            val endDate = calendar.timeInMillis
            binding.etEndDate.setText(dateFormat.format(Date(endDate)))
        }
    }

    private fun saveMember(generateInvoice: Boolean) {
        val name = binding.etName.text.toString()
        val phone = binding.etPhone.text.toString()
        val ageStr = binding.etAge.text.toString()
        val amountStr = binding.etAmount.text.toString()
        val paymentMethod = binding.actvPaymentMethod.text.toString()
        
        if (name.isEmpty() || phone.isEmpty() || ageStr.isEmpty() || selectedPlan == null || amountStr.isEmpty() || paymentMethod.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selectedStartDate
        calendar.add(Calendar.DAY_OF_YEAR, selectedPlan!!.durationDays)
        val endDate = calendar.timeInMillis
        val amount = amountStr.toDoubleOrNull() ?: 0.0

        val member = Member(
            name = name,
            phone = phone,
            address = binding.etAddress.text.toString(),
            age = ageStr.toIntOrNull() ?: 0,
            gender = binding.etGender.text.toString(),
            weight = binding.etWeight.text.toString(),
            planId = selectedPlan!!.planId,
            startDate = selectedStartDate,
            endDate = endDate,
            amount = amount,
            paymentMethod = paymentMethod,
            photoUri = photoUriString,
            status = "Active"
        )
        
        val invoiceNumber = "INV-${System.currentTimeMillis()}"
        
        val payment = Payment(
            memberId = 0, // Assigned correctly in ViewModel
            amount = amount,
            paymentDate = System.currentTimeMillis(),
            method = paymentMethod,
            invoiceNumber = invoiceNumber
        )

        viewModel.insertMember(member, payment)
        Toast.makeText(requireContext(), "Member saved successfully", Toast.LENGTH_SHORT).show()
        
        if (generateInvoice) {
            // TODO: Generate and show invoice
        }
        
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

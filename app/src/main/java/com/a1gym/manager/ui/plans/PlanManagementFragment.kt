package com.a1gym.manager.ui.plans

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.a1gym.manager.data.AppDatabase
import com.a1gym.manager.data.entity.Plan
import com.a1gym.manager.databinding.FragmentPlanManagementBinding
import com.a1gym.manager.repository.GymRepository
import com.a1gym.manager.viewmodel.GymViewModel
import com.a1gym.manager.viewmodel.GymViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PlanManagementFragment : Fragment() {

    private var _binding: FragmentPlanManagementBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GymViewModel
    private lateinit var planAdapter: PlanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanManagementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val database = AppDatabase.getDatabase(requireContext(), lifecycleScope)
        val repository = GymRepository(database.memberDao(), database.planDao(), database.paymentDao())
        val factory = GymViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[GymViewModel::class.java]

        setupRecyclerView()
        observePlans()
        
        binding.fabAddPlan.setOnClickListener {
            showAddPlanDialog()
        }
    }

    private fun setupRecyclerView() {
        planAdapter = PlanAdapter { plan ->
             // Edit or delete options logic on click
             showEditPlanDialog(plan)
        }
        binding.rvPlans.adapter = planAdapter
    }

    private fun observePlans() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allPlans.collectLatest { plans ->
                planAdapter.submitList(plans)
            }
        }
    }
    
    private fun showAddPlanDialog() {
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 40, 50, 10)

        val nameInput = EditText(requireContext())
        nameInput.hint = "Plan Name"
        layout.addView(nameInput)

        val durationInput = EditText(requireContext())
        durationInput.hint = "Duration (Days)"
        durationInput.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        layout.addView(durationInput)

        val priceInput = EditText(requireContext())
        priceInput.hint = "Price (₹)"
        priceInput.inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        layout.addView(priceInput)

        AlertDialog.Builder(requireContext())
            .setTitle("Add New Plan")
            .setView(layout)
            .setPositiveButton("Save") { _, _ ->
                val name = nameInput.text.toString()
                val duration = durationInput.text.toString().toIntOrNull()
                val price = priceInput.text.toString().toDoubleOrNull()
                
                if (name.isNotEmpty() && duration != null && price != null) {
                    val newPlan = Plan(planName = name, durationDays = duration, price = price)
                    viewModel.insertPlan(newPlan)
                    Toast.makeText(requireContext(), "Plan Added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Invalid Input", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun showEditPlanDialog(plan: Plan) {
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 40, 50, 10)

        val nameInput = EditText(requireContext())
        nameInput.setText(plan.planName)
        layout.addView(nameInput)

        val durationInput = EditText(requireContext())
        durationInput.setText(plan.durationDays.toString())
        durationInput.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        layout.addView(durationInput)

        val priceInput = EditText(requireContext())
        priceInput.setText(plan.price.toString())
        priceInput.inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        layout.addView(priceInput)

        AlertDialog.Builder(requireContext())
            .setTitle("Edit Plan")
            .setView(layout)
            .setPositiveButton("Update") { _, _ ->
                val name = nameInput.text.toString()
                val duration = durationInput.text.toString().toIntOrNull()
                val price = priceInput.text.toString().toDoubleOrNull()
                
                if (name.isNotEmpty() && duration != null && price != null) {
                    val updatedPlan = plan.copy(planName = name, durationDays = duration, price = price)
                    viewModel.updatePlan(updatedPlan)
                    Toast.makeText(requireContext(), "Plan Updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Invalid Input", Toast.LENGTH_SHORT).show()
                }
            }
            .setNeutralButton("Delete") { _, _ ->
                viewModel.deletePlan(plan)
                Toast.makeText(requireContext(), "Plan Deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvPlans.adapter = null
        _binding = null
    }
}

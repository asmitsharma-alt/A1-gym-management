package com.a1gym.manager.ui.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.a1gym.manager.data.AppDatabase
import com.a1gym.manager.data.entity.Payment
import com.a1gym.manager.databinding.FragmentPaymentHistoryBinding
import com.a1gym.manager.repository.GymRepository
import com.a1gym.manager.viewmodel.GymViewModel
import com.a1gym.manager.viewmodel.GymViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar

class PaymentHistoryFragment : Fragment() {

    private var _binding: FragmentPaymentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GymViewModel
    private lateinit var paymentAdapter: PaymentAdapter
    private var allPayments: List<Payment> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = AppDatabase.getDatabase(requireContext(), lifecycleScope)
        val repository = GymRepository(database.memberDao(), database.planDao(), database.paymentDao())
        val factory = GymViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[GymViewModel::class.java]

        setupRecyclerView()
        setupFilterChips()
        observePayments()
    }

    private fun setupRecyclerView() {
        paymentAdapter = PaymentAdapter()
        binding.rvPayments.apply {
            adapter = paymentAdapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
        }
    }

    private fun setupFilterChips() {
        binding.chipGroupFilter.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isEmpty()) return@setOnCheckedStateChangeListener
            val filtered = when (checkedIds.first()) {
                binding.chipAll.id -> allPayments
                binding.chipToday.id -> {
                    val todayStart = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, 0)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                    }.timeInMillis
                    allPayments.filter { it.paymentDate >= todayStart }
                }
                binding.chipUPI.id -> allPayments.filter { it.method.equals("UPI", ignoreCase = true) }
                binding.chipCash.id -> allPayments.filter { it.method.equals("Cash", ignoreCase = true) }
                binding.chipOnline.id -> allPayments.filter { it.method.equals("Online", ignoreCase = true) }
                else -> allPayments
            }
            paymentAdapter.submitList(filtered)
            updateEmptyState(filtered.isEmpty())
        }
    }

    private fun observePayments() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allPayments.collectLatest { payments ->
                allPayments = payments
                paymentAdapter.submitList(payments)
                updateEmptyState(payments.isEmpty())
            }
        }
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        binding.emptyState.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.rvPayments.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvPayments.adapter = null
        _binding = null
    }
}

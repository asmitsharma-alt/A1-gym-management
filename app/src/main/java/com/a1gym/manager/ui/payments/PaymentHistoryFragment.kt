package com.a1gym.manager.ui.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.a1gym.manager.data.AppDatabase
import com.a1gym.manager.databinding.FragmentPaymentHistoryBinding
import com.a1gym.manager.repository.GymRepository
import com.a1gym.manager.viewmodel.GymViewModel
import com.a1gym.manager.viewmodel.GymViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PaymentHistoryFragment : Fragment() {

    private var _binding: FragmentPaymentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GymViewModel
    private lateinit var paymentAdapter: PaymentAdapter

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
        observePayments()
    }

    private fun setupRecyclerView() {
        paymentAdapter = PaymentAdapter()
        binding.rvPayments.apply {
            adapter = paymentAdapter
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
        }
    }

    private fun observePayments() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allPayments.collectLatest { payments ->
                paymentAdapter.submitList(payments)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvPayments.adapter = null
        _binding = null
    }
}

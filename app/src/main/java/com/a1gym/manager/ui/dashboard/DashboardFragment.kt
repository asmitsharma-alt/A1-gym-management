package com.a1gym.manager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.a1gym.manager.R
import com.a1gym.manager.data.AppDatabase
import com.a1gym.manager.databinding.FragmentDashboardBinding
import com.a1gym.manager.repository.GymRepository
import com.a1gym.manager.ui.members.MemberAdapter
import com.a1gym.manager.viewmodel.GymViewModel
import com.a1gym.manager.viewmodel.GymViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GymViewModel
    private lateinit var memberAdapter: MemberAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Setup ViewModel
        val database = AppDatabase.getDatabase(requireContext(), lifecycleScope)
        val repository = GymRepository(database.memberDao(), database.planDao(), database.paymentDao())
        val factory = GymViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[GymViewModel::class.java]
        
        // Refresh statuses
        viewModel.refreshMemberStatuses()

        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        memberAdapter = MemberAdapter { member ->
            val bundle = Bundle().apply {
                putLong("memberId", member.id)
            }
            findNavController().navigate(R.id.memberProfileFragment, bundle)
        }
        binding.rvExpiringSoon.adapter = memberAdapter
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.totalMembersCount.collectLatest { count ->
                binding.tvTotalMembers.text = count.toString()
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.activeMembersCount.collectLatest { count ->
                binding.tvActiveMembers.text = count.toString()
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.expiredMembersCount.collectLatest { count ->
                binding.tvExpiredMembers.text = count.toString()
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getTodayRevenue().collectLatest { revenue ->
                binding.tvTodayRevenue.text = "₹${revenue ?: 0.0}"
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.expiringSoonMembers.collectLatest { members ->
                memberAdapter.submitList(members)
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.btnAddMember.setOnClickListener {
            findNavController().navigate(R.id.addMemberFragment)
        }
        binding.btnViewMembers.setOnClickListener {
            findNavController().navigate(R.id.memberListFragment)
        }
        binding.btnPayments.setOnClickListener {
            findNavController().navigate(R.id.paymentHistoryFragment)
        }
        binding.cardTotalMembers.setOnClickListener {
            findNavController().navigate(R.id.memberListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvExpiringSoon.adapter = null
        _binding = null
    }
}

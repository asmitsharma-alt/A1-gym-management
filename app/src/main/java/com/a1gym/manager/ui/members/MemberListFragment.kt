package com.a1gym.manager.ui.members

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.a1gym.manager.R
import com.a1gym.manager.data.AppDatabase
import com.a1gym.manager.databinding.FragmentMemberListBinding
import com.a1gym.manager.repository.GymRepository
import com.a1gym.manager.viewmodel.GymViewModel
import com.a1gym.manager.viewmodel.GymViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MemberListFragment : Fragment() {

    private var _binding: FragmentMemberListBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: GymViewModel
    private lateinit var repository: GymRepository
    private lateinit var memberAdapter: MemberAdapter
    private var currentFilter = "All"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemberListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Setup ViewModel
        val database = AppDatabase.getDatabase(requireContext(), lifecycleScope)
        repository = GymRepository(database.memberDao(), database.planDao(), database.paymentDao())
        val factory = GymViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[GymViewModel::class.java]

        setupRecyclerView()
        setupListeners()
        observeMembers()
    }

    private fun setupRecyclerView() {
        memberAdapter = MemberAdapter { member ->
            val bundle = Bundle().apply {
                putLong("memberId", member.id)
            }
            findNavController().navigate(R.id.memberProfileFragment, bundle)
        }
        binding.rvMembers.adapter = memberAdapter
    }

    private fun setupListeners() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchMembers(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnFilter.setOnClickListener { view ->
            val popup = PopupMenu(requireContext(), view)
            popup.menu.add("All Members")
            popup.menu.add("Active")
            popup.menu.add("Expired")
            popup.menu.add("Expiring Soon")
            
            popup.setOnMenuItemClickListener { item ->
                currentFilter = item.title.toString()
                binding.etSearch.text?.clear()
                observeMembers()
                true
            }
            popup.show()
        }

        binding.fabAddMember.setOnClickListener {
            findNavController().navigate(R.id.addMemberFragment)
        }
    }

    private fun observeMembers() {
        viewLifecycleOwner.lifecycleScope.launch {
            if (currentFilter == "All" || currentFilter == "All Members") {
                viewModel.allMembers.collectLatest { members ->
                    memberAdapter.submitList(members)
                }
            } else {
                repository.getMembersByStatus(currentFilter).collectLatest { members ->
                    memberAdapter.submitList(members)
                }
            }
        }
    }
    
    private fun searchMembers(query: String) {
        if (query.isEmpty()) {
            observeMembers()
            return
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repository.searchMembers(query).collectLatest { members ->
                // Apply filter if selected
                val filtered = if (currentFilter != "All" && currentFilter != "All Members") {
                    members.filter { it.status == currentFilter }
                } else {
                    members
                }
                memberAdapter.submitList(filtered)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvMembers.adapter = null
        _binding = null
    }
}

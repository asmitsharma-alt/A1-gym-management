package com.a1gym.manager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.a1gym.manager.data.entity.Member
import com.a1gym.manager.data.entity.Payment
import com.a1gym.manager.data.entity.Plan
import com.a1gym.manager.repository.GymRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

class GymViewModel(private val repository: GymRepository) : ViewModel() {

    val allMembers: StateFlow<List<Member>> = repository.allMembers
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalMembersCount: StateFlow<Int> = repository.totalMembersCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val activeMembersCount: StateFlow<Int> = repository.activeMembersCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val expiredMembersCount: StateFlow<Int> = repository.expiredMembersCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val expiringSoonMembers: StateFlow<List<Member>> = repository.expiringSoonMembers
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allPlans: StateFlow<List<Plan>> = repository.allPlans
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
        
    val allPayments: StateFlow<List<Payment>> = repository.allPayments
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // --- Member Operations ---
    
    fun insertMember(member: Member, payment: Payment?) = viewModelScope.launch {
        val memberId = repository.insertMember(member)
        if (payment != null) {
            val paymentWithMemberId = payment.copy(memberId = memberId)
            repository.insertPayment(paymentWithMemberId)
        }
    }

    fun updateMember(member: Member) = viewModelScope.launch {
        repository.updateMember(member)
    }

    fun deleteMember(member: Member) = viewModelScope.launch {
        repository.deleteMember(member)
    }

    // --- Plan Operations ---

    fun insertPlan(plan: Plan) = viewModelScope.launch {
        repository.insertPlan(plan)
    }

    fun updatePlan(plan: Plan) = viewModelScope.launch {
        repository.updatePlan(plan)
    }
    
    fun deletePlan(plan: Plan) = viewModelScope.launch {
        repository.deletePlan(plan)
    }
    
    // --- Payment Operations ---
    
    fun insertPayment(payment: Payment) = viewModelScope.launch {
        repository.insertPayment(payment)
    }

    // Daily Revenue Setup
    fun getTodayRevenue(): StateFlow<Double?> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val startOfDay = calendar.timeInMillis
        
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        val endOfDay = calendar.timeInMillis

        return repository.getRevenueBetween(startOfDay, endOfDay)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)
    }
    
    // Check and update member statuses automatically
    fun refreshMemberStatuses() = viewModelScope.launch {
        val members = repository.allMembers.stateIn(viewModelScope).value
        val currentTime = System.currentTimeMillis()
        val threeDaysInMillis = 3L * 24 * 60 * 60 * 1000
        
        members.forEach { member ->
            val timeUntilExpiry = member.endDate - currentTime
            
            val newStatus = when {
                timeUntilExpiry < 0 -> "Expired"
                timeUntilExpiry <= threeDaysInMillis -> "Expiring Soon"
                else -> "Active"
            }
            
            if (member.status != newStatus) {
                repository.updateMemberStatus(member.id, newStatus)
            }
        }
    }
}

class GymViewModelFactory(private val repository: GymRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GymViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GymViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

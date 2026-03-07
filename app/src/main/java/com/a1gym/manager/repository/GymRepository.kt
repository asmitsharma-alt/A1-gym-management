package com.a1gym.manager.repository

import com.a1gym.manager.data.dao.MemberDao
import com.a1gym.manager.data.dao.PaymentDao
import com.a1gym.manager.data.dao.PlanDao
import com.a1gym.manager.data.entity.Member
import com.a1gym.manager.data.entity.Payment
import com.a1gym.manager.data.entity.Plan
import kotlinx.coroutines.flow.Flow

class GymRepository(
    private val memberDao: MemberDao,
    private val planDao: PlanDao,
    private val paymentDao: PaymentDao
) {

    // Members
    val allMembers: Flow<List<Member>> = memberDao.getAllMembers()
    val activeMembersCount: Flow<Int> = memberDao.getActiveMembersCount()
    val expiredMembersCount: Flow<Int> = memberDao.getExpiredMembersCount()
    val totalMembersCount: Flow<Int> = memberDao.getTotalMembersCount()
    val expiringSoonMembers: Flow<List<Member>> = memberDao.getExpiringSoonMembers()

    suspend fun getMemberById(id: Long): Member? {
        return memberDao.getMemberById(id)
    }

    fun searchMembers(query: String): Flow<List<Member>> {
        return memberDao.searchMembers(query)
    }

    fun getMembersByStatus(status: String): Flow<List<Member>> {
        return memberDao.getMembersByStatus(status)
    }

    suspend fun insertMember(member: Member): Long {
        return memberDao.insert(member)
    }

    suspend fun updateMember(member: Member) {
        memberDao.update(member)
    }

    suspend fun deleteMember(member: Member) {
        memberDao.delete(member)
    }
    
    suspend fun updateMemberStatus(id: Long, status: String) {
        memberDao.updateMemberStatus(id, status)
    }

    // Plans
    val allPlans: Flow<List<Plan>> = planDao.getAllPlans()

    suspend fun insertPlan(plan: Plan) {
        planDao.insert(plan)
    }

    suspend fun updatePlan(plan: Plan) {
        planDao.update(plan)
    }

    suspend fun deletePlan(plan: Plan) {
        planDao.delete(plan)
    }

    // Payments
    val allPayments: Flow<List<Payment>> = paymentDao.getAllPayments()

    fun getPaymentsForMember(memberId: Long): Flow<List<Payment>> {
        return paymentDao.getPaymentsForMember(memberId)
    }
    
    fun getPaymentsBetween(start: Long, end: Long): Flow<List<Payment>> {
        return paymentDao.getPaymentsBetween(start, end)
    }

    fun getRevenueBetween(start: Long, end: Long): Flow<Double?> {
        return paymentDao.getRevenueBetween(start, end)
    }

    suspend fun insertPayment(payment: Payment) {
        paymentDao.insert(payment)
    }
}

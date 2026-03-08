package com.a1gym.manager.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\f\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010$J\u0018\u0010%\u001a\u0004\u0018\u00010\u00102\u0006\u0010&\u001a\u00020\'H\u0086@\u00a2\u0006\u0002\u0010(J\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\n2\u0006\u0010*\u001a\u00020+J\"\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000f0\n2\u0006\u0010-\u001a\u00020\'2\u0006\u0010.\u001a\u00020\'J\u001a\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000f0\n2\u0006\u00100\u001a\u00020\'J\u001e\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\n2\u0006\u0010-\u001a\u00020\'2\u0006\u0010.\u001a\u00020\'J\u0016\u00103\u001a\u00020\'2\u0006\u0010 \u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010!J\u0016\u00104\u001a\u00020\u001f2\u0006\u00105\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u00106J\u0016\u00107\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010$J\u001a\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\n2\u0006\u00109\u001a\u00020+J\u0016\u0010:\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010!J\u001e\u0010;\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\'2\u0006\u0010*\u001a\u00020+H\u0086@\u00a2\u0006\u0002\u0010<J\u0016\u0010=\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010$R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000f0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\rR\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u000f0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\rR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\rR\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\r\u00a8\u0006>"}, d2 = {"Lcom/a1gym/manager/repository/GymRepository;", "", "memberDao", "Lcom/a1gym/manager/data/dao/MemberDao;", "planDao", "Lcom/a1gym/manager/data/dao/PlanDao;", "paymentDao", "Lcom/a1gym/manager/data/dao/PaymentDao;", "(Lcom/a1gym/manager/data/dao/MemberDao;Lcom/a1gym/manager/data/dao/PlanDao;Lcom/a1gym/manager/data/dao/PaymentDao;)V", "activeMembersCount", "Lkotlinx/coroutines/flow/Flow;", "", "getActiveMembersCount", "()Lkotlinx/coroutines/flow/Flow;", "allMembers", "", "Lcom/a1gym/manager/data/entity/Member;", "getAllMembers", "allPayments", "Lcom/a1gym/manager/data/entity/Payment;", "getAllPayments", "allPlans", "Lcom/a1gym/manager/data/entity/Plan;", "getAllPlans", "expiredMembersCount", "getExpiredMembersCount", "expiringSoonMembers", "getExpiringSoonMembers", "totalMembersCount", "getTotalMembersCount", "deleteMember", "", "member", "(Lcom/a1gym/manager/data/entity/Member;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePlan", "plan", "(Lcom/a1gym/manager/data/entity/Plan;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMemberById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMembersByStatus", "status", "", "getPaymentsBetween", "start", "end", "getPaymentsForMember", "memberId", "getRevenueBetween", "", "insertMember", "insertPayment", "payment", "(Lcom/a1gym/manager/data/entity/Payment;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPlan", "searchMembers", "query", "updateMember", "updateMemberStatus", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePlan", "app_release"})
public final class GymRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.a1gym.manager.data.dao.MemberDao memberDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.a1gym.manager.data.dao.PlanDao planDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.a1gym.manager.data.dao.PaymentDao paymentDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Member>> allMembers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Integer> activeMembersCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Integer> expiredMembersCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Integer> totalMembersCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Member>> expiringSoonMembers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Plan>> allPlans = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Payment>> allPayments = null;
    
    public GymRepository(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.dao.MemberDao memberDao, @org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.dao.PlanDao planDao, @org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.dao.PaymentDao paymentDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Member>> getAllMembers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getActiveMembersCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getExpiredMembersCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getTotalMembersCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Member>> getExpiringSoonMembers() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMemberById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.a1gym.manager.data.entity.Member> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Member>> searchMembers(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Member>> getMembersByStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String status) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertMember(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Member member, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateMember(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Member member, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteMember(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Member member, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateMemberStatus(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Plan>> getAllPlans() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertPlan(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Plan plan, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updatePlan(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Plan plan, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deletePlan(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Plan plan, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Payment>> getAllPayments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Payment>> getPaymentsForMember(long memberId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Payment>> getPaymentsBetween(long start, long end) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Double> getRevenueBetween(long start, long end) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertPayment(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Payment payment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}
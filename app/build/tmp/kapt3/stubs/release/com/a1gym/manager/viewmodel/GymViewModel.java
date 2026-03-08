package com.a1gym.manager.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\fJ\u000e\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0012J\u000e\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u0006J\u0018\u0010!\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\f2\b\u0010\"\u001a\u0004\u0018\u00010\u000fJ\u000e\u0010#\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u000fJ\u000e\u0010$\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0012J\u0006\u0010%\u001a\u00020\u001bJ\u000e\u0010&\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\fJ\u000e\u0010\'\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0012R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\tR\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\tR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\tR\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\t\u00a8\u0006("}, d2 = {"Lcom/a1gym/manager/viewmodel/GymViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/a1gym/manager/repository/GymRepository;", "(Lcom/a1gym/manager/repository/GymRepository;)V", "activeMembersCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "getActiveMembersCount", "()Lkotlinx/coroutines/flow/StateFlow;", "allMembers", "", "Lcom/a1gym/manager/data/entity/Member;", "getAllMembers", "allPayments", "Lcom/a1gym/manager/data/entity/Payment;", "getAllPayments", "allPlans", "Lcom/a1gym/manager/data/entity/Plan;", "getAllPlans", "expiredMembersCount", "getExpiredMembersCount", "expiringSoonMembers", "getExpiringSoonMembers", "totalMembersCount", "getTotalMembersCount", "deleteMember", "Lkotlinx/coroutines/Job;", "member", "deletePlan", "plan", "getTodayRevenue", "", "insertMember", "payment", "insertPayment", "insertPlan", "refreshMemberStatuses", "updateMember", "updatePlan", "app_release"})
public final class GymViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.a1gym.manager.repository.GymRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.a1gym.manager.data.entity.Member>> allMembers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> totalMembersCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> activeMembersCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> expiredMembersCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.a1gym.manager.data.entity.Member>> expiringSoonMembers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.a1gym.manager.data.entity.Plan>> allPlans = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.a1gym.manager.data.entity.Payment>> allPayments = null;
    
    public GymViewModel(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.repository.GymRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.a1gym.manager.data.entity.Member>> getAllMembers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getTotalMembersCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getActiveMembersCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getExpiredMembersCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.a1gym.manager.data.entity.Member>> getExpiringSoonMembers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.a1gym.manager.data.entity.Plan>> getAllPlans() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.a1gym.manager.data.entity.Payment>> getAllPayments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insertMember(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Member member, @org.jetbrains.annotations.Nullable()
    com.a1gym.manager.data.entity.Payment payment) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job updateMember(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Member member) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteMember(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Member member) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insertPlan(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Plan plan) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job updatePlan(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Plan plan) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deletePlan(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Plan plan) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insertPayment(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Payment payment) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getTodayRevenue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job refreshMemberStatuses() {
        return null;
    }
}
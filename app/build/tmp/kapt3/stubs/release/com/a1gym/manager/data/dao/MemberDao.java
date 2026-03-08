package com.a1gym.manager.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\'J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\bH\'J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\'J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\bH\'J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\b2\u0006\u0010\u0013\u001a\u00020\u0014H\'J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bH\'J\u0016\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\b2\u0006\u0010\u0018\u001a\u00020\u0014H\'J\u0016\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u001b\u00a8\u0006\u001c"}, d2 = {"Lcom/a1gym/manager/data/dao/MemberDao;", "", "delete", "", "member", "Lcom/a1gym/manager/data/entity/Member;", "(Lcom/a1gym/manager/data/entity/Member;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveMembersCount", "Lkotlinx/coroutines/flow/Flow;", "", "getAllMembers", "", "getExpiredMembersCount", "getExpiringSoonMembers", "getMemberById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMembersByStatus", "status", "", "getTotalMembersCount", "insert", "searchMembers", "query", "update", "updateMemberStatus", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
@androidx.room.Dao()
public abstract interface MemberDao {
    
    @androidx.room.Query(value = "SELECT * FROM members ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Member>> getAllMembers();
    
    @androidx.room.Query(value = "SELECT * FROM members WHERE status = :status ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Member>> getMembersByStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String status);
    
    @androidx.room.Query(value = "SELECT * FROM members WHERE status = \'Expiring Soon\' ORDER BY endDate ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Member>> getExpiringSoonMembers();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM members")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> getTotalMembersCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM members WHERE status = \'Active\'")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> getActiveMembersCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM members WHERE status = \'Expired\'")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> getExpiredMembersCount();
    
    @androidx.room.Query(value = "SELECT * FROM members WHERE name LIKE \'%\' || :query || \'%\' OR phone LIKE \'%\' || :query || \'%\'")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Member>> searchMembers(@org.jetbrains.annotations.NotNull()
    java.lang.String query);
    
    @androidx.room.Query(value = "SELECT * FROM members WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMemberById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.a1gym.manager.data.entity.Member> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Member member, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Member member, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Member member, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE members SET status = :status WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateMemberStatus(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}
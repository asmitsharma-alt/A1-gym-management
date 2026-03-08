package com.a1gym.manager.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J$\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\'J\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\u000f\u001a\u00020\fH\'J \u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\'J\u0016\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0013"}, d2 = {"Lcom/a1gym/manager/data/dao/PaymentDao;", "", "delete", "", "payment", "Lcom/a1gym/manager/data/entity/Payment;", "(Lcom/a1gym/manager/data/entity/Payment;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPayments", "Lkotlinx/coroutines/flow/Flow;", "", "getPaymentsBetween", "startTimestamp", "", "endTimestamp", "getPaymentsForMember", "memberId", "getRevenueBetween", "", "insert", "app_release"})
@androidx.room.Dao()
public abstract interface PaymentDao {
    
    @androidx.room.Query(value = "SELECT * FROM payments ORDER BY paymentDate DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Payment>> getAllPayments();
    
    @androidx.room.Query(value = "SELECT * FROM payments WHERE paymentDate >= :startTimestamp AND paymentDate <= :endTimestamp ORDER BY paymentDate DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Payment>> getPaymentsBetween(long startTimestamp, long endTimestamp);
    
    @androidx.room.Query(value = "SELECT SUM(amount) FROM payments WHERE paymentDate >= :startTimestamp AND paymentDate <= :endTimestamp")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Double> getRevenueBetween(long startTimestamp, long endTimestamp);
    
    @androidx.room.Query(value = "SELECT * FROM payments WHERE memberId = :memberId ORDER BY paymentDate DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.a1gym.manager.data.entity.Payment>> getPaymentsForMember(long memberId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Payment payment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.a1gym.manager.data.entity.Payment payment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}
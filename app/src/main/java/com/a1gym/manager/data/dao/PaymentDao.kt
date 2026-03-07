package com.a1gym.manager.data.dao

import androidx.room.*
import com.a1gym.manager.data.entity.Payment
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Query("SELECT * FROM payments ORDER BY paymentDate DESC")
    fun getAllPayments(): Flow<List<Payment>>

    @Query("SELECT * FROM payments WHERE paymentDate >= :startTimestamp AND paymentDate <= :endTimestamp ORDER BY paymentDate DESC")
    fun getPaymentsBetween(startTimestamp: Long, endTimestamp: Long): Flow<List<Payment>>

    @Query("SELECT SUM(amount) FROM payments WHERE paymentDate >= :startTimestamp AND paymentDate <= :endTimestamp")
    fun getRevenueBetween(startTimestamp: Long, endTimestamp: Long): Flow<Double?>
    
    @Query("SELECT * FROM payments WHERE memberId = :memberId ORDER BY paymentDate DESC")
    fun getPaymentsForMember(memberId: Long): Flow<List<Payment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(payment: Payment): Long

    @Delete
    suspend fun delete(payment: Payment)
}

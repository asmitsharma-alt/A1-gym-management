package com.a1gym.manager.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments")
data class Payment(
    @PrimaryKey(autoGenerate = true)
    val paymentId: Long = 0,
    val memberId: Long,
    val amount: Double,
    val paymentDate: Long,
    val method: String,
    val invoiceNumber: String
)

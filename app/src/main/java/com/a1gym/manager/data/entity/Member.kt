package com.a1gym.manager.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "members")
data class Member(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val phone: String,
    val address: String,
    val age: Int,
    val gender: String,
    val height: String? = null,
    val weight: String? = null,
    val planId: Long,
    val startDate: Long,
    val endDate: Long,
    val amount: Double,
    val paymentMethod: String,
    val photoUri: String? = null,
    val status: String // Active, Expired, Expiring Soon
)

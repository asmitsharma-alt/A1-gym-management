package com.a1gym.manager.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plans")
data class Plan(
    @PrimaryKey(autoGenerate = true)
    val planId: Long = 0,
    val planName: String,
    val durationDays: Int, // 30, 90, 180, 365
    val price: Double
)

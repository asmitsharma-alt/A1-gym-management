package com.a1gym.manager.data.dao

import androidx.room.*
import com.a1gym.manager.data.entity.Plan
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {
    @Query("SELECT * FROM plans ORDER BY durationDays ASC")
    fun getAllPlans(): Flow<List<Plan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plan: Plan): Long

    @Update
    suspend fun update(plan: Plan)

    @Delete
    suspend fun delete(plan: Plan)
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(plans: List<Plan>)
}

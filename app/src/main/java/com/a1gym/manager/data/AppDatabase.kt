package com.a1gym.manager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.a1gym.manager.data.dao.MemberDao
import com.a1gym.manager.data.dao.PaymentDao
import com.a1gym.manager.data.dao.PlanDao
import com.a1gym.manager.data.entity.Member
import com.a1gym.manager.data.entity.Payment
import com.a1gym.manager.data.entity.Plan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Member::class, Plan::class, Payment::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao
    abstract fun planDao(): PlanDao
    abstract fun paymentDao(): PaymentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gym_manager_db"
                )
                .addCallback(AppDatabaseCallback(scope))
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class AppDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.planDao())
                    }
                }
            }
            
            suspend fun populateDatabase(planDao: PlanDao) {
                // Pre-populate with default plans
                val defaultPlans = listOf(
                    Plan(planName = "1 Month", durationDays = 30, price = 1000.0),
                    Plan(planName = "3 Months", durationDays = 90, price = 2500.0),
                    Plan(planName = "6 Months", durationDays = 180, price = 4500.0),
                    Plan(planName = "12 Months", durationDays = 365, price = 8000.0)
                )
                planDao.insertAll(defaultPlans)
            }
        }
    }
}

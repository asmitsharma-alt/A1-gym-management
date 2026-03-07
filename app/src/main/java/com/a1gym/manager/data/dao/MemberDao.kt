package com.a1gym.manager.data.dao

import androidx.room.*
import com.a1gym.manager.data.entity.Member
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {
    @Query("SELECT * FROM members ORDER BY name ASC")
    fun getAllMembers(): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE status = :status ORDER BY name ASC")
    fun getMembersByStatus(status: String): Flow<List<Member>>
    
    @Query("SELECT * FROM members WHERE status = 'Expiring Soon' ORDER BY endDate ASC")
    fun getExpiringSoonMembers(): Flow<List<Member>>
    
    @Query("SELECT COUNT(*) FROM members")
    fun getTotalMembersCount(): Flow<Int>
    
    @Query("SELECT COUNT(*) FROM members WHERE status = 'Active'")
    fun getActiveMembersCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM members WHERE status = 'Expired'")
    fun getExpiredMembersCount(): Flow<Int>

    @Query("SELECT * FROM members WHERE name LIKE '%' || :query || '%' OR phone LIKE '%' || :query || '%'")
    fun searchMembers(query: String): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE id = :id")
    suspend fun getMemberById(id: Long): Member?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(member: Member): Long

    @Update
    suspend fun update(member: Member)

    @Delete
    suspend fun delete(member: Member)
    
    @Query("UPDATE members SET status = :status WHERE id = :id")
    suspend fun updateMemberStatus(id: Long, status: String)
}

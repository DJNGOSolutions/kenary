package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Role


@Dao
interface RoleDao {
    @Query("SELECT * FROM role_table")
    fun getAllRole(): LiveData<List<Role>>

    @Query("SELECT * FROM role_table Where idRole_User = :idRole_User")
    fun getRoleById(idRole_User: Int)  : LiveData<Role>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(role: Role)

    @Query("DELETE FROM role_table")
    fun deleteAll()
}
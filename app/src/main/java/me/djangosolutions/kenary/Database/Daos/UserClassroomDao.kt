package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Subscription
import me.djangosolutions.kenary.Entity.UserClassroom

/**
 * Created by Marcelo on 10/07/2018.
 */
@Dao
interface UserClassroomDao {
    @Query("SELECT * FROM userxclassroom_table")
    fun getAllUserClassroom(): LiveData<List<UserClassroom>>

    @Query("SELECT * FROM userxclassroom_table")
    fun getUserClassroomById(idUserClassroom: Int): LiveData<UserClassroom>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userClassroom: UserClassroom)

    @Query("DELETE FROM userxclassroom_table")
    fun deleteAll()
}
package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Classroom

/**
 * Created by Marcelo on 10/07/2018.
 */
@Dao
interface ClassroomDao {
    @Query("SELECT * FROM classroom_table")
    fun getAllClassroom(): LiveData<List<Classroom>>

    @Query("SELECT * FROM classroom_table WHERE idClassroom =:idClassroom")
    fun getClassroomById(idClassroom: Int): LiveData<Classroom>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(classroom: Classroom)

    @Query("DELETE FROM classroom_table")
    fun deleteAll()
}
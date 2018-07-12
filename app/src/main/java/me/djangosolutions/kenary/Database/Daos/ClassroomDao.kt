package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.ClassroomEntity

/**
 * Created by Marcelo on 12/07/2018.
 */
@Dao
interface ClassroomDao {
    @Query("SELECT * FROM classroom_table")
    fun getAllClassrooms(): LiveData<List<ClassroomEntity>>

    @Query("SELECT * FROM classroom_table WHERE idClassroom = :idClassroom")
    fun getClassroomById(idClassroom: Int): LiveData<ClassroomEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(classroomEntity: ClassroomEntity)

    @Query("DELETE FROM classroom_table")
    fun deleteAll()
}
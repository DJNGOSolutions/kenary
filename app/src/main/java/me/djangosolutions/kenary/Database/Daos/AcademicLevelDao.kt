package me.djangosolutions.kenary.Database.Daos


import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.AcademicLevel


@Dao
interface AcademicLevelDao {
    @Query("SELECT * FROM academicLevel_table")
    fun getAllidAcademicLevel(): LiveData<List<AcademicLevel>>

    @Query("SELECT * FROM academicLevel_table Where idAcademicLevel = :idAcademicLevel")
    fun getAcademicLevelById(idAcademicLevel: Int)  : LiveData<AcademicLevel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(AcademicLevel: AcademicLevel)

    @Query("DELETE FROM academicLevel_table")
    fun deleteAll()
}
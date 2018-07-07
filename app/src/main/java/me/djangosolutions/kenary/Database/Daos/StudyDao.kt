package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Study


@Dao
interface StudyDao {
    @Query("SELECT * FROM study_table")
    fun getAllStudy(): LiveData<List<Study>>

    @Query("SELECT * FROM study_table Where idStudy = :idStudy")
    fun geStudyById(idStudy: Int)  : LiveData<Study>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(study: Study)

    @Query("DELETE FROM study_table")
    fun deleteAll()
}
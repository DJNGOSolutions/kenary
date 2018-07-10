package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Tutorial


@Dao
interface TutorialDao {
    @Query("SELECT * FROM tutorial_table")
    fun getAllTutoria(): LiveData<List<Tutorial>>

    @Query("SELECT * FROM tutorial_table Where idTutorial = :idTutorial")
    fun getTutoriaById(idTutorial: Int)  : LiveData<Tutorial>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(tutorial: Tutorial)

    @Query("DELETE FROM tutorial_table")
    fun deleteAll()
}
package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.TutorialEntity

/**
 * Created by Marcelo on 12/07/2018.
 */
@Dao
interface TutorialDaoA {
    @Query("SELECT * FROM tutorial_table_a")
    fun getAllTutorials(): LiveData<List<TutorialEntity>>

    @Query("SELECT * FROM tutorial_table_a WHERE idTutorial = :IdTutorial")
    fun getTutorialById(idClass: Int): LiveData<TutorialEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(tutorialEntity: TutorialEntity)

    @Query("DELETE FROM tutorial_table_a")
    fun deleteAll()
}
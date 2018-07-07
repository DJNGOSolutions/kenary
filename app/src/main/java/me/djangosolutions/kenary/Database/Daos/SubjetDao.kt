package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Subject


@Dao
interface SubjetDao {
    @Query("SELECT * FROM subject_table")
    fun getAllSuject(): LiveData<List<Subject>>

    @Query("SELECT * FROM subject_table Where idSubject= :idSubject")
    fun getSubjectById(idSubject: Int)  : LiveData<Subject>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(subject: Subject)

    @Query("DELETE FROM subject_table")
    fun deleteAll()
}
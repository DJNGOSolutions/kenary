package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.SubjectTopic


@Dao
interface SubjectTopicDao {
    @Query("SELECT * FROM subjectxtopic_table")
    fun getAllSubject(): LiveData<List<SubjectTopic>>

    @Query("SELECT * FROM subjectxtopic_table Where idSubjectxTopic = :idSubjectxTopic")
    fun getSubjectById(idSubjectxTopic: Int)  : LiveData<SubjectTopic>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(subjectTopic: SubjectTopic)

    @Query("DELETE FROM subjectxtopic_table")
    fun deleteAll()
}
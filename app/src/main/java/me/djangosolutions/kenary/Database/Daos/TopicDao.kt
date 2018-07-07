package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Topic


@Dao
interface TopicDao {
    @Query("SELECT * FROM topic_table")
    fun getAllTopic(): LiveData<List<Topic>>

    @Query("SELECT * FROM topic_table Where idTopic = :idTopic")
    fun getTopicById(idTopic: Int)  : LiveData<Topic>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(topic: Topic)

    @Query("DELETE FROM topic_table")
    fun deleteAll()
}
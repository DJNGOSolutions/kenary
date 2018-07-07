package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Assistance


@Dao
interface AssistanceDao {
    @Query("SELECT * FROM assistance_table")
    fun getAllAssistance(): LiveData<List<Assistance>>

    @Query("SELECT * FROM assistance_table Where idAssistance = :idAssistance")
    fun getAssistanceById(idAssistance: Int)  : LiveData<Assistance>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(assistance: Assistance)

    @Query("DELETE FROM assistance_table")
    fun deleteAll()
}
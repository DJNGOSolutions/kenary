package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Rate
import me.djangosolutions.kenary.Entity.Role
import me.djangosolutions.kenary.Entity.Session


@Dao
interface SessionDao {
    @Query("SELECT * FROM session_table")
    fun getAllSession(): LiveData<List<Session>>

    @Query("SELECT * FROM session_table Where idSession = :idSession")
    fun getSessionById(idSession: Int)  : LiveData<Session>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(session: Session)

    @Query("DELETE FROM session_table")
    fun deleteAll()
}
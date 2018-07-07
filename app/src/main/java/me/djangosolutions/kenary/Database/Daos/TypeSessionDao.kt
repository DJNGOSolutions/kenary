package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.TypeSession


@Dao
interface TypeSessionDao {
    @Query("SELECT * FROM type_session_table")
    fun getAllTypeSession(): LiveData<List<TypeSession>>

    @Query("SELECT * FROM type_session_table Where idType_Session = :idType_Session")
    fun getTypeSessionById(idType_Session: Int)  : LiveData<TypeSession>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(typeSession: TypeSession)

    @Query("DELETE FROM type_session_table")
    fun deleteAll()
}
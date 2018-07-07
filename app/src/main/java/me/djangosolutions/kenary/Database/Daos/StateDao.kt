package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.State

@Dao
interface StateDao {
    @Query("SELECT * FROM state_table")
    fun getAllState(): LiveData<List<State>>

    @Query("SELECT * FROM state_table Where idState = :idState")
    fun getStateById(idState: Int)  : LiveData<State>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(state: State)

    @Query("DELETE FROM state_table")
    fun deleteAll()
}
package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Rate


@Dao
interface RateDao {
    @Query("SELECT * FROM rate_table")
    fun getAllRate(): LiveData<List<Rate>>

    @Query("SELECT * FROM rate_table Where idRate = :idRate")
    fun getRateById(idRate: Int)  : LiveData<Rate>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(rate: Rate)

    @Query("DELETE FROM rate_table")
    fun deleteAll()
}
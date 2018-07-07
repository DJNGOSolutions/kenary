package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.City


@Dao
interface CityDao {
    @Query("SELECT * FROM city_table")
    fun getAllCity(): LiveData<List<City>>

    @Query("SELECT * FROM city_table Where idCity = :idCity")
    fun getCityById(idCity: Int)  : LiveData<City>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(City: City)

    @Query("DELETE FROM city_table")
    fun deleteAll()
}
package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Place


@Dao
interface PlaceDao {
    @Query("SELECT * FROM place_table")
    fun getAllPlace(): LiveData<List<Place>>

    @Query("SELECT * FROM place_table Where idPlace_Session = :idPlace_Session")
    fun getPlaceById(idPlace_Session: Int)  : LiveData<Place>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(place: Place)

    @Query("DELETE FROM place_table")
    fun deleteAll()
}
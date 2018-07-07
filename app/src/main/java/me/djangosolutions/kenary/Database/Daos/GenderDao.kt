package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Gender


@Dao
interface GenderDao {
    @Query("SELECT * FROM gender_table")
    fun getAllGender(): LiveData<List<Gender>>

    @Query("SELECT * FROM gender_table Where idGender_User = :idGender_User")
    fun getGenderById(idGender_User: Int)  : LiveData<Gender>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(gender: Gender)

    @Query("DELETE FROM gender_table")
    fun deleteAll()
}
package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Phone


@Dao
interface PhoneDao {
    @Query("SELECT * FROM phone_table")
    fun getAllPhone(): LiveData<List<Phone>>

    @Query("SELECT * FROM phone_table Where idPhonexUser = :idPhonexUser")
    fun getPhoneById(idPhonexUser: Int)  : LiveData<Phone>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(phone: Phone)

    @Query("DELETE FROM phone_table")
    fun deleteAll()
}
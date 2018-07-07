package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Institution


@Dao
interface InstitutionDao {
    @Query("SELECT * FROM institution_table")
    fun getAllInstitution(): LiveData<List<Institution>>

    @Query("SELECT * FROM institution_table Where idInstitution = :idInstitution")
    fun getInstitutionById(idInstitution: Int)  : LiveData<Institution>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(institution: Institution)

    @Query("DELETE FROM institution_table")
    fun deleteAll()
}
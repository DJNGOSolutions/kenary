package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Class


@Dao
interface ClassDao {
    @Query("SELECT * FROM class_table")
    fun getAllClass(): LiveData<List<Class>>

    @Query("SELECT * FROM class_table Where idClass = :idClass")
    fun getClassById(idClass: Int)  : LiveData<Class>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(Class:Class)

    @Query("DELETE FROM class_table")
    fun deleteAll()
}
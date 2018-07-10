package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Category

/**
 * Created by Marcelo on 09/07/2018.
 */
@Dao
interface CategoryDao {
    @Query("SELECT * FROM category_table")
    fun getAllCategory(): LiveData<List<Category>>

    @Query("SELECT * FROM category_table WHERE idCategory = :idCategory")
    fun getCategoryById(idCategory: Int): LiveData<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: Category)

    @Query("DELETE FROM category_table")
    fun deleteAll()
}
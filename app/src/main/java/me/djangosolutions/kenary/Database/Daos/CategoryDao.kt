package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.CategoryEntity

/**
 * Created by Marcelo on 12/07/2018.
 */
@Dao
interface CategoryDao {
    @Query("SELECT * FROM category_table")
    fun getAllCategories(): LiveData<List<CategoryEntity>>

    @Query("SELECT * FROM category_table WHERE idCategory = :IdCategory")
    fun getCategoryById(idClass: Int): LiveData<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(categoryEntity: CategoryEntity)

    @Query("DELETE FROM category_table")
    fun deleteAll()
}
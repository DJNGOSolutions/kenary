package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Product


@Dao
interface ProductDao {
    @Query("SELECT * FROM product_table")
    fun getAllProduct(): LiveData<List<Product>>

    @Query("SELECT * FROM product_table Where ProductID = :ProductID")
    fun getProductById(ProductID: Int)  : LiveData<Product>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: Product)

    @Query("DELETE FROM product_table")
    fun deleteAll()
}
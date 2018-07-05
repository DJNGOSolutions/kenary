package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(@PrimaryKey @ColumnInfo(name = "ProductID") var ProductID: Int = 0,
                   @ColumnInfo(name = "Code") var Code: String = "",
                   @ColumnInfo(name = "Price") var Price: Int = 0) {
}


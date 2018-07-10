package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Marcelo on 09/07/2018.
 */

@Entity(tableName = "category_table")
data class Category (@PrimaryKey @ColumnInfo(name = "idCategory") var IdCategory: Int = 0,
                     @ColumnInfo(name = "categoryPhoto") var CategoryPhoto: String = "",
                     @ColumnInfo(name = "categoryName") var CategoryName: String = ""){
}
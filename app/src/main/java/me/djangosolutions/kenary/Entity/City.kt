package me.djangosolutions.kenary.Entity


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "city_table")
data class City(@PrimaryKey @ColumnInfo(name = "idCity") var idCity: Int = 0,
                   @ColumnInfo(name = "idState") var idState: Int = 0,
                   @ColumnInfo(name = "CityName") var CityName: String = "") {
}

package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "place_table")
data class Place(@PrimaryKey @ColumnInfo(name = "idPlace_Session") var idPlace_Session: Int = 0,
                   @ColumnInfo(name = "Place") var Place: String = "") {
}


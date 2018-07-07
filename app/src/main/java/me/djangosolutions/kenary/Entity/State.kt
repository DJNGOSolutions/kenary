package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "state_table")
data class State(@PrimaryKey @ColumnInfo(name = "idState") var idState: Int = 0,
                   @ColumnInfo(name = "StateName") var StateName: String = "") {
}


package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "rate_table")
data class Rate(@PrimaryKey @ColumnInfo(name = "idRate") var idRate: Int = 0,
                   @ColumnInfo(name = "Rate") var Rate: String = "") {
}


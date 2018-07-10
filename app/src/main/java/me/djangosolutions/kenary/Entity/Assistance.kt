package me.djangosolutions.kenary.Entity


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "assistance_table")
data class Assistance(@PrimaryKey @ColumnInfo(name = "idAssistance") var IdAssistance: Int = 0,
                   @ColumnInfo(name = "idUser") var IdUser: Int = 0,
                   @ColumnInfo(name = "idSession") var IdSession: Int = 0,
                   @ColumnInfo(name = "TotalFeeSession") var TotalFeeSession: String = "") {
}

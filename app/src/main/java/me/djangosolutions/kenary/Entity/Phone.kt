package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "phone_table")
data class Phone(@PrimaryKey @ColumnInfo(name = "idUser") var IdUser: Int = 0,
                        @ColumnInfo(name = "Phone") var Phone: String = "") {
}

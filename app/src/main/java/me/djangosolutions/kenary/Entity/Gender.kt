package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "gender_table")
data class Gender(@PrimaryKey @ColumnInfo(name = "idGender_User") var IdGender_User: Int = 0,
                   @ColumnInfo(name = "Gender") var Gender: String = "") {
}


package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "role_table")
data class Role(@PrimaryKey @ColumnInfo(name = "idRole_User") var IdRole_User: Int = 0,
                   @ColumnInfo(name = "Role") var Role: String = "") {
}

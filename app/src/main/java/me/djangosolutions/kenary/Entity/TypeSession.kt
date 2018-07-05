package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "type_session_table")
data class TypeSession(@PrimaryKey @ColumnInfo(name = "idType_Session") var idType_Session: Int = 0,
                   @ColumnInfo(name = "Type_Session") var Type_Session: String = "") {
}


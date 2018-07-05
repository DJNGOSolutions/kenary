package me.djangosolutions.kenary.Entity


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "class_table")
data class Class(@PrimaryKey @ColumnInfo(name = "idClass") var idClass: Int = 0,
                   @ColumnInfo(name = "idInstitution") var idInstitution: Int = 0,
                   @ColumnInfo(name = "idSubject") var idSubject: Int = 0) {
}


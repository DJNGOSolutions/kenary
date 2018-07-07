package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "study_table")
data class Study(@PrimaryKey @ColumnInfo(name = "idStudy") var idStudy: Int = 0,
                   @ColumnInfo(name = "idUser") var idUser: Int = 0,
                   @ColumnInfo(name = "idSubject") var idSubject: Int = 0) {
}

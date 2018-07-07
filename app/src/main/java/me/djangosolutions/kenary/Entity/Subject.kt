package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "subject_table")
data class Subject(@PrimaryKey @ColumnInfo(name = "idSubject") var idSubject: Int = 0,
                   @ColumnInfo(name = "SubjectName") var SubjectName: String = "") {
}

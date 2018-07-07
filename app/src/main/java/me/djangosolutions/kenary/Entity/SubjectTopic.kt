package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "subjectxtopic_table")
data class SubjectTopic(@PrimaryKey @ColumnInfo(name = "idSubjectxTopic") var idSubjectxTopic: Int = 0,
@ColumnInfo(name = "idSubject") var idSubject: Int = 0,
@ColumnInfo(name = "idTopic") var idTopic: Int = 0) {
}

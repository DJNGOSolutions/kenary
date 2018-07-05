package me.djangosolutions.kenary.Entity


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "academicLevel_table")
data class AcademicLevel(@PrimaryKey @ColumnInfo(name = "idAcademicLevel") var idAcademicLevel: Int = 0,
                   @ColumnInfo(name = "AcademicLevel") var AcademicLevel: String = "") {
}


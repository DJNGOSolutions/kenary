package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "institution_table")
data class Institution(@PrimaryKey @ColumnInfo(name = "idInstitution") var idInstitution: Int = 0,
                   @ColumnInfo(name = "idUser") var idUser: Int = 0,
                   @ColumnInfo(name = "idSubject") var idSubject: Int = 0,
                   @ColumnInfo(name = "idState") var idState: Int = 0,
                   @ColumnInfo(name = "idCity") var idCity: Int = 0,
                   @ColumnInfo(name = "InstitutionName") var InstitutionName: String = "") {
}

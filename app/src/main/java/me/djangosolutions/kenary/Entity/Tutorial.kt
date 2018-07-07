package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tutorial_table")
data class Tutorial(@PrimaryKey @ColumnInfo(name = "idTutorial") var idTutorial: Int = 0,
                   @ColumnInfo(name = "idUser") var idUser: Int = 0,
                   @ColumnInfo(name = "idSubject") var idSubject: Int = 0) {
}

package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "session_table")
data class Session(@PrimaryKey @ColumnInfo(name = "idSession") var idSession: Int = 0,
                @ColumnInfo(name = "idSessionPlace") var idSessionPlace: Int = 0,
                @ColumnInfo(name = "idSubject") var idSubject: Int = 0,
                @ColumnInfo(name = "idTopic") var idTopic: Int = 0,
                @ColumnInfo(name = "idTypeSession") var idTypeSession: Int = 0,
                @ColumnInfo(name = "idTypePayment") var idTypePayment: Int = 0,
                @ColumnInfo(name = "SessionDate") var institucion: String = "",
                @ColumnInfo(name = "SessionTimeStart") var rolUser: String = "",
                @ColumnInfo(name = "SessionTimeEnd") var materiaUser: String = "",
                @ColumnInfo(name = "SessionFee") var nombreUser: String = "") {
}

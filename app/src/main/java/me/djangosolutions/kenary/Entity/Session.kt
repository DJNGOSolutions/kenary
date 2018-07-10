package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "session_table")
data class Session(@PrimaryKey @ColumnInfo(name = "idSession") var IdSession: Int = 0,
                   @ColumnInfo(name = "idSessionPlace") var IdSessionPlace: Int = 0,
                   @ColumnInfo(name = "idClassroom") var IdClassroom: Int = 0,
                   @ColumnInfo(name = "idTypeSession") var IdTypeSession: Int = 0,
                   @ColumnInfo(name = "idTypePayment") var idTypePayment: Int = 0,
                   @ColumnInfo(name = "sessionDate") var SessionDate: String = "",
                   @ColumnInfo(name = "sessionTimeStart") var SessionTimeStart: String = "",
                   @ColumnInfo(name = "sessionTimeEnd") var SessionTimeEnd: String = "",
                   @ColumnInfo(name = "sessionFee") var SessionFee: String = "",
                   @ColumnInfo(name = "sessionRate") var SessionRate: Float = 0.0F,
                   @ColumnInfo(name = "sessionReview") var SessionReview: String = "") {
}

package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "table_user")
data class User(@PrimaryKey @ColumnInfo(name = "idUser") var _idUser: Int = 0,
                @ColumnInfo(name = "idGenderUser ") var idGenderUser : Int = 0,
                @ColumnInfo(name = "idAcademicLevelUser") var idAcademicLevelUser: Int = 0,
                @ColumnInfo(name = "idRoleUser ") var idRoleUser : Int = 0,
                @ColumnInfo(name = "idRateUser") var idRateUser: Int = 0,
                @ColumnInfo(name = "UserPhoto") var UserPhoto: String = "",
                @ColumnInfo(name = "UserName") var UserName: String = "",
                @ColumnInfo(name = "UserAge ") var UserAge : Int = 0,
                @ColumnInfo(name = "UserBirthday") var UserBirthday : String = "",
                @ColumnInfo(name = "UserEmail") var UserEmail: String = "",
                @ColumnInfo(name = "UserDescription ") var UserDescription: String = "") {
}


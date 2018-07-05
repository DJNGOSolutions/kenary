package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
data class User(@PrimaryKey @ColumnInfo(name = "idUser") var _idUser: Int = 0,
                @ColumnInfo(name = "IdGenderUser ") var IdGenderUser : Int = 0,
                @ColumnInfo(name = "IdAcademicLevelUser") var IdAcademicLevelUser: Int = 0,
                @ColumnInfo(name = "IdRoleUser ") var nIdRoleUser : Int = 0,
                @ColumnInfo(name = "IdRateUser") var IdRateUser: Int = 0,
                @ColumnInfo(name = "UserPhoto") var UserPhoto: String = "",
                @ColumnInfo(name = "UserName") var UserName: String = "",
                @ColumnInfo(name = "UserAge ") var UserAge : Int = 0,
                @ColumnInfo(name = "UserEmail") var UserEmail: String = "",
                @ColumnInfo(name = "UserDescription ") var UserDescription: String = "") {
}


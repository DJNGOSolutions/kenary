package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Marcelo on 12/07/2018.
 */
@Entity(tableName = "user_table")
data class UserEntity(@PrimaryKey @ColumnInfo(name = "idUser") var IdUser: Int = 0,
                @ColumnInfo(name = "idGoogle") var IdGoogle: Int = 0,
                @ColumnInfo(name = "idTipo") var IdTipo: Int = 0,
                @ColumnInfo(name = "userPhoto") var UserPhoto: String = "",
                @ColumnInfo(name = "userName") var UserName: String = "",
                @ColumnInfo(name = "name") var Name: String = "",
                @ColumnInfo(name = "userInstitution") var UserInstitution: String = "",
                @ColumnInfo(name = "userPassword") var UserPassword: String = "") {
}
package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Marcelo on 09/07/2018.
 */
@Entity(tableName = "subscription_table")
data class Subscription (@PrimaryKey @ColumnInfo(name = "idSubscription") var IdSubscription: Int = 0,
                         @ColumnInfo (name = "idClassroom") var IdClassroom: Int = 0,
                         @ColumnInfo (name = "idUser") var IdUser: Int = 0){
}
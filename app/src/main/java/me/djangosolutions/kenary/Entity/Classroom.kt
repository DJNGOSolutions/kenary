package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Marcelo on 09/07/2018.
 */

@Entity(tableName = "classroom_table")
data class Classroom(@PrimaryKey @ColumnInfo(name = "idClassroom") var IdClassroom: Int = 0,
                     @ColumnInfo(name = "IdSubject") var IdSubject: Int = 0,
                     @ColumnInfo(name = "IdTopic") var IdTopic: Int = 0){
}
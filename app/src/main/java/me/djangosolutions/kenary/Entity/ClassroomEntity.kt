package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Marcelo on 12/07/2018.
 */
@Entity(tableName = "classroom_table")
data class ClassroomEntity (@PrimaryKey @ColumnInfo(name = "idClassroom") var IdClassroom: Int = 0,
                           @ColumnInfo(name = "categoryName") var CategoryName: String = "",
                           @ColumnInfo(name = "subjectName") var SubjectName: String = "",
                           @ColumnInfo(name = "tutorName") var TutorName: String = "",
                           @ColumnInfo(name = "ClassroomPhoto") var ClassroomPhoto: String = "",
                           @ColumnInfo(name = "ClassroomTheme") var ClassroomTheme: String = "",
                           @ColumnInfo(name = "ClassroomPlace") var ClassroomPlace: String = "",
                           @ColumnInfo(name = "ClassroomTime") var ClassroomTime: String = "",
                           @ColumnInfo(name = "ClassroomDate") var ClassroomDate: String = "",
                           @ColumnInfo(name = "ClassroomPrice") var ClassroomPrice: String = ""){
}
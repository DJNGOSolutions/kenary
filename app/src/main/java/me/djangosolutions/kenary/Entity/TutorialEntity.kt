package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Marcelo on 12/07/2018.
 */
@Entity(tableName = "tutorial_tableNEW")
data class TutorialEntity (@PrimaryKey @ColumnInfo(name = "idTutorial") var IdTutorial: Int = 0,
                           @ColumnInfo(name = "categoryName") var CategoryName: String = "",
                           @ColumnInfo(name = "subjectName") var SubjectName: String = "",
                           @ColumnInfo(name = "tutorName") var TutorName: String = "",
                           @ColumnInfo(name = "CustomerName") var CustomerName: String = "",
                           @ColumnInfo(name = "tutorialPhoto") var TutorialPhoto: String = "",
                           @ColumnInfo(name = "tutorialTheme") var TutorialTheme: String = "",
                           @ColumnInfo(name = "tutorialPlace") var TutorialPlace: String = "",
                           @ColumnInfo(name = "tutorialTime") var TutorialTime: String = "",
                           @ColumnInfo(name = "tutorialDate") var TutorialDate: String = "",
                           @ColumnInfo(name = "tutorialPrice") var TutorialPrice: String = ""){
}
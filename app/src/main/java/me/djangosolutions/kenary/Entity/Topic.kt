package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "topic_table")
data class Topic(@PrimaryKey @ColumnInfo(name = "idTopic") var idTopic: Int = 0,
                   @ColumnInfo(name = "TopicName") var TopicName: String = "") {
}


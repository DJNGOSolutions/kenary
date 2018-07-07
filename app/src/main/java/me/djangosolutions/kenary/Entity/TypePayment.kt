package me.djangosolutions.kenary.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "type_payment_table")
data class TypePayment(@PrimaryKey @ColumnInfo(name = "idTypePayment_Session") var idTypePayment_Session: Int = 0,
                   @ColumnInfo(name = "TypePayment") var TypePayment: String = "") {
}

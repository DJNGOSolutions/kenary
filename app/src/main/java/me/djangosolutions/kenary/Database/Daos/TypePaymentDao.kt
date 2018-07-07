package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.TypePayment
import me.djangosolutions.kenary.Entity.User

@Dao
interface TypePaymentDao {
    @Query("SELECT * FROM type_payment_table")
    fun getAllTypePayment(): LiveData<List<TypePayment>>

    @Query("SELECT * FROM type_payment_table Where idTypePayment_Session = :idTypePayment_Session")
    fun getTypePaymentById(idTypePayment_Session: Int)  : LiveData<TypePayment>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(typePayment: TypePayment)

    @Query("DELETE FROM type_payment_table")
    fun deleteAll()
}
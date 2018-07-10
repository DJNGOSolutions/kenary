package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.Category
import me.djangosolutions.kenary.Entity.Subscription

/**
 * Created by Marcelo on 10/07/2018.
 */
@Dao
interface SubscriptionDao {
    @Query("SELECT * FROM subscription_table")
    fun getAllSubscription(): LiveData<List<Subscription>>

    @Query("SELECT * FROM subscription_table WHERE idSubscription =:idSubscription")
    fun getSubscriptionById(idSubscription: Int): LiveData<Subscription>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(subscription: Subscription)

    @Query("DELETE FROM subscription_table")
    fun deleteAll()
}
package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.ClassroomDao
import me.djangosolutions.kenary.Database.Daos.SubscriptionDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Classroom
import me.djangosolutions.kenary.Entity.Subscription
import me.djangosolutions.kenary.Webserver.AmaiAPI

/**
 * Created by Marcelo on 09/07/2018.
 */
class SubscriptionRepository(application: Application) {
    var mSubscriptionDao: SubscriptionDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mSubscriptionDao = db?.subscriptionDao()
    }

    fun getAll(): LiveData<List<Subscription>> = mSubscriptionDao!!.getAllSubscription()

    fun getAllbyId(id: Int): LiveData<Subscription> = mSubscriptionDao!!.getSubscriptionById(id)

    fun insert(subscription: Subscription){
        CompositeDisposable().add(Observable.fromCallable { mSubscriptionDao!!.insert(subscription) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
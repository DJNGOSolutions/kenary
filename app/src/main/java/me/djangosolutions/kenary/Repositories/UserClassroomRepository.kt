package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.SubscriptionDao
import me.djangosolutions.kenary.Database.Daos.UserClassroomDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Subscription
import me.djangosolutions.kenary.Entity.UserClassroom
import me.djangosolutions.kenary.Webserver.AmaiAPI

/**
 * Created by Marcelo on 09/07/2018.
 */
class UserClassroomRepository(application: Application) {
    var mUserClassroomDao: UserClassroomDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mUserClassroomDao = db?.userClassroomDao()
    }

    fun getAll(): LiveData<List<UserClassroom>> = mUserClassroomDao!!.getAllUserClassroom()

    fun getAllbyId(id: Int): LiveData<UserClassroom> = mUserClassroomDao!!.getUserClassroomById(id)

    fun insert(userClassroom: UserClassroom){
        CompositeDisposable().add(Observable.fromCallable { mUserClassroomDao!!.insert(userClassroom) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
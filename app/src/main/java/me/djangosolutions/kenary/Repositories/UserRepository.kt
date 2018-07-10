package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.UserDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.User
import me.djangosolutions.kenary.Webserver.AmaiAPI
import me.djangosolutions.kenary.util.amaiiService
import org.jetbrains.anko.doAsync

class UserRepository(application: Application) {
    var mUserDao: UserDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mUserDao = db?.userDao()
    }

    fun getAll(): LiveData<List<User>> = mUserDao!!.getAll()

    fun insert(user: User){
        CompositeDisposable().add(Observable.fromCallable { mUserDao!!.insert(user) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    fun putUp2Date(email: String, password: String){
        doAsync {
            val response = amaiiService.login(email, password).execute()
            Log.d("PUTUPTODATE", response.body())
        }
    }
}
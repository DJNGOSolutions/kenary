package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import android.content.Context
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.UserDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.User
import me.djangosolutions.kenary.R
import me.djangosolutions.kenary.Webserver.AmaiAPI
import me.djangosolutions.kenary.util.amaiiService
import org.jetbrains.anko.doAsync
import retrofit2.Call

class UserRepository(private var application: Application) {
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

    fun insertSharedPref(token: String){
        val sharedPref = application.getSharedPreferences("log", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()){
            putString(application.getString(R.string.saved_token), token)
            apply()
        }
    }

    fun getToken(email: String, password: String){
       CompositeDisposable().add(Observable.fromCallable {Log.d("PUTUPTODATE",  amaiiService.login(email, password).execute().body())
       insertSharedPref(amaiiService.login(email, password).execute().body()!!)}.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe())
    }
}
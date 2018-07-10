package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.SessionDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Session
import me.djangosolutions.kenary.Webserver.AmaiAPI

class SessionRepository (application: Application){
    var mSessionDao: SessionDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mSessionDao = db?.sessionDao()
    }

    fun getAll(): LiveData<List<Session>> = mSessionDao!!.getAllSession()

    fun getAllbyId(id: Int): LiveData<Session> = mSessionDao!!.getSessionById(id)

    fun insert(session: Session){
        CompositeDisposable().add(Observable.fromCallable { mSessionDao!!.insert(session) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
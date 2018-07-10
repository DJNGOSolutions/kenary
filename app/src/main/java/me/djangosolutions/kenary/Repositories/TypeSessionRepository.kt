package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.TypeSessionDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.TypeSession
import me.djangosolutions.kenary.Webserver.AmaiAPI

class TypeSessionRepository (application: Application){
    var mTypeSessionDao: TypeSessionDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mTypeSessionDao = db?.typeSessionDao()
    }

    fun getAll(): LiveData<List<TypeSession>> = mTypeSessionDao!!.getAllTypeSession()

    fun getAllbyId(id: Int): LiveData<TypeSession> = mTypeSessionDao!!.getTypeSessionById(id)

    fun insert(typeSession: TypeSession){
        CompositeDisposable().add(Observable.fromCallable { mTypeSessionDao!!.insert(typeSession) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
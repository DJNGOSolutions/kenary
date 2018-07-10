package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.ClassDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Class
import me.djangosolutions.kenary.Webserver.AmaiAPI

class ClassRepository(application: Application) {
    var mClassDao: ClassDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mClassDao = db?.classDao()
    }

    fun getAll(): LiveData<List<Class>> = mClassDao!!.getAllClass()

    fun getAllbyId(id: Int): LiveData<Class> = mClassDao!!.getClassById(id)

    fun insert(classs: Class){
        CompositeDisposable().add(Observable.fromCallable { mClassDao!!.insert(classs) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
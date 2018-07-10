package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.CompositeGeneratedAdaptersObserver
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.GenderDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Gender
import me.djangosolutions.kenary.Webserver.AmaiAPI

class GenderRepository (application: Application){
    var mGenderDao: GenderDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mGenderDao = db?.genderDao()
    }

    fun getAll(): LiveData<List<Gender>> = mGenderDao!!.getAllGender()

    fun getAllbyId(id: Int): LiveData<Gender> = mGenderDao!!.getGenderById(id)

    fun insert(gender: Gender){
        CompositeDisposable().add(Observable.fromCallable { mGenderDao!!.insert(gender)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
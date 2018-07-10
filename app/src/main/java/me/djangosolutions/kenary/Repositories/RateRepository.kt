package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.RateDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Rate
import me.djangosolutions.kenary.Webserver.AmaiAPI

class RateRepository (application: Application){
    var mRateDao: RateDao? = null
    var AmaiAPI: AmaiAPI? = null

    init{
        val db = KenaryDatabase.getDatabase(application)
        mRateDao = db?.rateDao()
    }

    fun getAll(): LiveData<List<Rate>> = mRateDao!!.getAllRate()

    fun insert(rate: Rate){
        CompositeDisposable().add(Observable.fromCallable { mRateDao!!.insert(rate) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
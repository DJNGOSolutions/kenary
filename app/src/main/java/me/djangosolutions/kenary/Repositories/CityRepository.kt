package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.CityDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.City
import me.djangosolutions.kenary.Webserver.AmaiAPI

class CityRepository(application: Application) {
    var mCityDao: CityDao? = null
    var AmaiAPI: AmaiAPI? = null
    init {
        val db = KenaryDatabase.getDatabase(application)
        mCityDao = db?.cityDao()

    }

    fun getAll() : LiveData<List<City>> = mCityDao!!.getAllCity()

    fun getAllbyId(id: Int) : LiveData<City> = mCityDao!!.getCityById(id)

    fun insert(city: City){
        CompositeDisposable().add(Observable.fromCallable { mCityDao!!.insert(city) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.PlaceDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Place
import me.djangosolutions.kenary.Webserver.AmaiAPI

class PlaceRepository (application: Application){
    var mPlaceDao: PlaceDao? = null
    var AmaiAPI: AmaiAPI? = null

    init{
        val db = KenaryDatabase.getDatabase(application)
        mPlaceDao = db?.placeDao()
    }

    fun getAll(): LiveData<List<Place>> = mPlaceDao!!.getAllPlace()

    fun insert(place: Place){
        CompositeDisposable().add(Observable.fromCallable { mPlaceDao!!.insert(place) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
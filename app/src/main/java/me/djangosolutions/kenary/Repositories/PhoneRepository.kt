package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.PhoneDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Phone
import me.djangosolutions.kenary.Webserver.AmaiAPI

class PhoneRepository (application: Application) {
    var mPhoneDao: PhoneDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mPhoneDao = db?.phoneDao()
    }

    fun getAll(): LiveData<List<Phone>> = mPhoneDao!!.getAllPhone()

    fun insert(phone: Phone){
        CompositeDisposable().add(Observable.fromCallable { mPhoneDao!!.insert(phone)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
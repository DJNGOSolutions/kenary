package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.TypePaymentDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.TypePayment
import me.djangosolutions.kenary.Webserver.AmaiAPI

class TypePaymentRepository (application: Application){
    var mTypePaymentDao: TypePaymentDao? = null
    var AmaiAPI: AmaiAPI? = null

    init{
        val db = KenaryDatabase.getDatabase(application)
        mTypePaymentDao = db?.typePaymentDao()
    }

    fun getAll(): LiveData<List<TypePayment>> = mTypePaymentDao!!.getAllTypePayment()

    fun getAllById(id: Int): LiveData<TypePayment> = mTypePaymentDao!!.getTypePaymentById(id)

    fun insert(typePayment: TypePayment){
        CompositeDisposable().add(Observable.fromCallable { mTypePaymentDao!!.insert(typePayment) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
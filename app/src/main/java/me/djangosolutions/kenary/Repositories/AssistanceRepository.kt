package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.AssistanceDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Assistance
import me.djangosolutions.kenary.Webserver.AmaiAPI

class AssistanceRepository(application: Application) {
    var mAssistanceDao: AssistanceDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mAssistanceDao = db?.assistanceDao()
    }

    fun getAll(): LiveData<List<Assistance>> = mAssistanceDao!!.getAllAssistance()

    fun getAllbyId(id: Int): LiveData<Assistance> = mAssistanceDao!!.getAssistanceById(id)

    fun insert(assistance: Assistance){
        CompositeDisposable().add(Observable.fromCallable { mAssistanceDao!!.insert(assistance) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
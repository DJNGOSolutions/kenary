package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.AcademicLevelDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.AcademicLevel
import me.djangosolutions.kenary.Webserver.AmaiAPI

class AcademicLevelRepository(application: Application) {
    var mAcademicLevelDao: AcademicLevelDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mAcademicLevelDao = db?.academicLevelDao()
    }

    fun getAll(): LiveData<List<AcademicLevel>> = mAcademicLevelDao!!.getAllidAcademicLevel()

    fun getAllbyId(id: Int): LiveData<AcademicLevel> = mAcademicLevelDao!!.getAcademicLevelById(id)

    fun insert(academicLevel: AcademicLevel){
        CompositeDisposable().add(Observable.fromCallable { mAcademicLevelDao!!.insert(academicLevel) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
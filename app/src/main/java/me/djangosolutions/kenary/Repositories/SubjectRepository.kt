package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.SubjectDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Subject
import me.djangosolutions.kenary.Webserver.AmaiAPI

class SubjectRepository (application: Application){
    var mSubjectDao: SubjectDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mSubjectDao = db?.subjectDao()
    }

    fun getAll(): LiveData<List<Subject>> = mSubjectDao!!.getAllSuject()

    fun getAllbyId(id: Int): LiveData<Subject> = mSubjectDao!!.getSubjectById(id)

    fun insert(subject: Subject){
        CompositeDisposable().add(Observable.fromCallable { mSubjectDao!!.insert(subject)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.SubjectTopicDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.SubjectTopic
import me.djangosolutions.kenary.Webserver.AmaiAPI

class SubjectTopicRepository (application: Application){
    var mSubjectTopicDao: SubjectTopicDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mSubjectTopicDao = db?.subjectTopicDao()
    }

    fun getAll(): LiveData<List<SubjectTopic>> = mSubjectTopicDao!!.getAllSubject()

    fun getAllbyId(id: Int): LiveData<SubjectTopic> = mSubjectTopicDao!!.getSubjectById(id)

    fun insert(subjectTopic: SubjectTopic){
        CompositeDisposable().add(Observable.fromCallable { mSubjectTopicDao!!.insert(subjectTopic) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
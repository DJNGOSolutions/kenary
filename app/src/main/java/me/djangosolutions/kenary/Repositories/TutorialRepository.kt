package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.TutorialDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Tutorial
import me.djangosolutions.kenary.Webserver.AmaiAPI

class TutorialRepository(application: Application) {
    var mTutorialDao: TutorialDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mTutorialDao = db?.tutorialDao()
    }

    fun getAll(): LiveData<List<Tutorial>> = mTutorialDao!!.getAllTutoria()

    fun getAllbyId(id: Int): LiveData<Tutorial> = mTutorialDao!!.getTutoriaById(id)

    fun insert(tutorial: Tutorial){
        CompositeDisposable().add(Observable.fromCallable { mTutorialDao!!.insert(tutorial) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
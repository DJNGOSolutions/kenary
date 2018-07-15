package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.TutorialDaoA
import me.djangosolutions.kenary.Database.Daos.UserDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.TutorialEntity
import me.djangosolutions.kenary.Entity.UserEntity

/**
 * Created by Marcelo on 12/07/2018.
 */
class TutorialRepository(application: Application) {
    var mTutorialDao: TutorialDaoA? = null

    init{
        val db = KenaryDatabase.getDatabase(application)
        mTutorialDao = db?.tutorialDaoA()
    }

    fun getAll(): LiveData<List<TutorialEntity>> = mTutorialDao!!.getAllTutorials()

    fun getAllByID(id: Int): LiveData<TutorialEntity> = mTutorialDao!!.getTutorialById(id)

    fun insert(tutorialEntity: TutorialEntity){
        CompositeDisposable().add(Observable.fromCallable { mTutorialDao!!.insert(tutorialEntity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.CategoryDao
import me.djangosolutions.kenary.Database.Daos.ClassroomDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Category
import me.djangosolutions.kenary.Entity.Classroom
import me.djangosolutions.kenary.Webserver.AmaiAPI

/**
 * Created by Marcelo on 09/07/2018.
 */
class ClassroomRepository(application: Application) {
    var mClassroomDao: ClassroomDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mClassroomDao = db?.classroomDao()
    }

    fun getAll(): LiveData<List<Classroom>> = mClassroomDao!!.getAllClassroom()

    fun getAllbyId(id: Int): LiveData<Classroom> = mClassroomDao!!.getClassroomById(id)

    fun insert(classroom: Classroom){
        CompositeDisposable().add(Observable.fromCallable { mClassroomDao!!.insert(classroom) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
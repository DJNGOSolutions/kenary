package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.ClassroomDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.ClassroomEntity

/**
 * Created by Marcelo on 12/07/2018.
 */
class ClassroomRepository (application: Application){
    var mClassroomDao: ClassroomDao? = null

    init{
        val db = KenaryDatabase.getDatabase(application)
        mClassroomDao = db?.classroomDao()
    }

    fun getAll(): LiveData<List<ClassroomEntity>> = mClassroomDao!!.getAllClassrooms()

    fun getClassRoomByID(id: Int): LiveData<ClassroomEntity> = mClassroomDao!!.getClassroomById(id)

    fun insert(classroomEntity: ClassroomEntity){
        CompositeDisposable().add(Observable.fromCallable { mClassroomDao!!.insert(classroomEntity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
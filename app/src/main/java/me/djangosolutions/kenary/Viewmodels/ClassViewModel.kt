package me.djangosolutions.kenary.Viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class ClassViewModel(application: Application): AndroidViewModel(application) {
    internal var mRepository: ClassRepository? = null

    init {
        mRepository = ClassRepository(application)
    }

    fun getAll(): LiveData<List<Class>> = mRepository!!.getAll()

    fun getAllbyId(id: Int): LiveData<Class> = mRepository!!.getAllbyId(id)

    fun insert(classs: Class) = mRepository!!.insert(classs)
}
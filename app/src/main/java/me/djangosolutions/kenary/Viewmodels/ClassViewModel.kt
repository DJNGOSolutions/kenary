package me.djangosolutions.kenary.Viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import me.djangosolutions.kenary.Entity.Class
import me.djangosolutions.kenary.Repositories.ClassRepository

class ClassViewModel(application: Application): AndroidViewModel(application) {
    internal var mRepository: ClassRepository? = null

    init {
        mRepository = ClassRepository(application)
    }

    fun getAll(): LiveData<List<me.djangosolutions.kenary.Entity.Class>> = mRepository!!.getAll()

    fun getAllbyId(id: Int): LiveData<me.djangosolutions.kenary.Entity.Class> = mRepository!!.getAllbyId(id)

    fun insert(classs: Class) = mRepository!!.insert(classs)
}
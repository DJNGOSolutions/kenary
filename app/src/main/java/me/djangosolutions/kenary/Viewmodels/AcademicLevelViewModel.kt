package me.djangosolutions.kenary.Viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import me.djangosolutions.kenary.Entity.AcademicLevel
import me.djangosolutions.kenary.Repositories.AcademicLevelRepository

class AcademicLevelViewModel(application: Application): AndroidViewModel(application){
    internal var mRepository: AcademicLevelRepository? = null

    init {
        mRepository = AcademicLevelRepository(application)
    }

    fun getAll(): LiveData<List<AcademicLevel>> = mRepository!!.getAll()

    fun getAllbyId(id: Int): LiveData<AcademicLevel> = mRepository!!.getAllbyId(id)

    fun insert(academicLevel: AcademicLevel) = mRepository!!.insert(academicLevel)
}
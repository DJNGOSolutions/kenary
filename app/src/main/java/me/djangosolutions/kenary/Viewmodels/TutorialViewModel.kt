package me.djangosolutions.kenary.Viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class TutorialViewModel(application: Application): AndroidViewModel(application) {
    internal var mRepository: TutorialRepository? = null

    init {
        mRepository = TutorialRepository(application)
    }

    fun getAll(): LiveData<List<Tutorial>> = mRepository!!.getAll()

    fun getAllbyId(id: Int): LiveData<Tutorial> = mRepository!!.getAllbyId(id)

    fun insert(tutorial: Tutorial) = mRepository!!.insert(tutorial)
}
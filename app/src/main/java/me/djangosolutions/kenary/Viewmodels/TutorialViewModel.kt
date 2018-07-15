package me.djangosolutions.kenary.Viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import me.djangosolutions.kenary.Entity.TutorialEntity
import me.djangosolutions.kenary.Repositories.TutorialRepository

/**
 * Created by Marcelo on 12/07/2018.
 */
class TutorialViewModel (application: Application): AndroidViewModel(application) {
    internal var mRepository: TutorialRepository? = null

    init {
        mRepository = TutorialRepository(application)
    }

    fun getAll(): LiveData<List<TutorialEntity>> = mRepository!!.getAll()

    fun getAllbyId(id: Int): LiveData<TutorialEntity> = mRepository!!.getAllByID(id)

    fun insert(tutorialEntity: TutorialEntity) = mRepository!!.insert(tutorialEntity)
}
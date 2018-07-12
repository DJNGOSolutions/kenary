package me.djangosolutions.kenary.Viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import me.djangosolutions.kenary.Entity.TutorialEntity
import me.djangosolutions.kenary.Entity.UserEntity
import me.djangosolutions.kenary.Repositories.TutorialRepositoryA
import me.djangosolutions.kenary.Repositories.UserRepository

/**
 * Created by Marcelo on 12/07/2018.
 */
class TutorialViewModelA (application: Application): AndroidViewModel(application) {
    internal var mRepository: TutorialRepositoryA? = null

    init {
        mRepository = TutorialRepositoryA(application)
    }

    fun getAll(): LiveData<List<TutorialEntity>> = mRepository!!.getAll()

    fun getAllbyId(id: Int): LiveData<TutorialEntity> = mRepository!!.getAllByID(id)

    fun insert(tutorialEntity: TutorialEntity) = mRepository!!.insert(tutorialEntity)
}
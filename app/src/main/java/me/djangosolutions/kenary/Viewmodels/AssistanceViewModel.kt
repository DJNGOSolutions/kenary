package me.djangosolutions.kenary.Viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import me.djangosolutions.kenary.Entity.Assistance
import me.djangosolutions.kenary.Repositories.AssistanceRepository

class AssistanceViewModel(application: Application): AndroidViewModel(application) {
    internal var mRepository: AssistanceRepository? = null

    init {
        mRepository = AssistanceRepository(application)
    }

    fun getAll(): LiveData<List<Assistance>> = mRepository!!.getAll()

    fun getAllbyId(id: Int): LiveData<Assistance> = mRepository!!.getAllbyId(id)

    fun insert(assistance: Assistance) = mRepository!!.insert(assistance)
}
package me.djangosolutions.kenary.Viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class CityViewModel(application: Application): AndroidViewModel(application) {
    internal var mRepository: CityRepository? = null

    init {
        mRepository = CityRepository(application)
    }

    fun getAll(): LiveData<List<City>> = mRepository!!.getAll()

    fun getAllbyId(id: Int) = mRepository!!.getAllbyId(id)

    fun insert(city: City) = mRepository!!.insert(city)
}
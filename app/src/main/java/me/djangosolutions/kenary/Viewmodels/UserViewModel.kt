package me.djangosolutions.kenary.Viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import me.djangosolutions.kenary.Entity.Class
import me.djangosolutions.kenary.Entity.UserEntity
import me.djangosolutions.kenary.Repositories.ClassRepository
import me.djangosolutions.kenary.Repositories.UserRepository

/**
 * Created by Marcelo on 12/07/2018.
 */
class UserViewModel (application: Application): AndroidViewModel(application) {
    internal var mRepository: UserRepository? = null

    init {
        mRepository = UserRepository(application)
    }

    fun getAll(): LiveData<List<UserEntity>> = mRepository!!.getAll()

    fun getAllbyId(id: Int): LiveData<UserEntity> = mRepository!!.getUserbyId(id)

    fun insert(userEntity: UserEntity) = mRepository!!.insert(userEntity)
}
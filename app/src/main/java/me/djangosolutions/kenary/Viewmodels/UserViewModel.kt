package me.djangosolutions.kenary.Viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import me.djangosolutions.kenary.Entity.User
import me.djangosolutions.kenary.Repositories.UserRepository

class UserViewModel(application: Application): AndroidViewModel(application){
    internal var mRepository: UserRepository? = null

    init {
        mRepository = UserRepository(application)
    }

    fun getAll(): LiveData<List<User>> = mRepository!!.getAll()

    fun insert(user: User) = mRepository!!.insert(user)

    fun putUp2Date(email: String, password: String) = mRepository!!.getToken(email, password)
}
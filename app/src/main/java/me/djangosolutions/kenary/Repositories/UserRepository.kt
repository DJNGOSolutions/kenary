package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.UserDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.UserEntity
import me.djangosolutions.kenary.Webserver.AmaiAPI

/**
 * Created by Marcelo on 12/07/2018.
 */
class UserRepository(application: Application) {
    var mUserDao: UserDao? = null
    var AmaiAPI: AmaiAPI? = null

    init{
        val db = KenaryDatabase.getDatabase(application)
        mUserDao = db?.userDao()
    }

    fun getAll(): LiveData<List<UserEntity>> = mUserDao!!.getAllUsers()

    fun getUserbyId(id: Int): LiveData<UserEntity> = mUserDao!!.getUserById(id)

    fun insert(userEntity: UserEntity){
        CompositeDisposable().add(Observable.fromCallable { mUserDao!!.insert(userEntity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
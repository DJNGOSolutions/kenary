package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.RoleDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Role
import me.djangosolutions.kenary.Webserver.AmaiAPI

class RoleRepository (application: Application){
    var mRoleDao: RoleDao? = null
    var AmaiAPI: AmaiAPI? = null

    init{
        val db = KenaryDatabase.getDatabase(application)
        mRoleDao = db?.roleDao()
    }

    fun getAll(): LiveData<List<Role>> = mRoleDao!!.getAllRole()

    fun getAllbyId(id: Int): LiveData<Role> = mRoleDao!!.getRoleById(id)

    fun insert(role: Role){
        CompositeDisposable().add(Observable.fromCallable { mRoleDao!!.insert(role) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
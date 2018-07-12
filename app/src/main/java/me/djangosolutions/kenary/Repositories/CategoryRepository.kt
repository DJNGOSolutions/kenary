package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.CategoryDao
import me.djangosolutions.kenary.Database.Daos.ClassroomDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.CategoryEntity
import me.djangosolutions.kenary.Entity.ClassroomEntity
import me.djangosolutions.kenary.Webserver.AmaiAPI

/**
 * Created by Marcelo on 12/07/2018.
 */
class CategoryRepository(application: Application) {
    var mCategoryDao: CategoryDao? = null
    var AmaiAPI: AmaiAPI? = null

    init{
        val db = KenaryDatabase.getDatabase(application)
        mCategoryDao = db?.categoryDao()
    }

    fun getAll(): LiveData<List<CategoryEntity>> = mCategoryDao!!.getAllCategories()

    fun getUserbyId(id: Int): LiveData<CategoryEntity> = mCategoryDao!!.getCategoryById(id)

    fun insert(categoryEntity: CategoryEntity){
        CompositeDisposable().add(Observable.fromCallable { mCategoryDao!!.insert(categoryEntity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
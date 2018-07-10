package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.AssistanceDao
import me.djangosolutions.kenary.Database.Daos.CategoryDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Assistance
import me.djangosolutions.kenary.Entity.Category
import me.djangosolutions.kenary.Webserver.AmaiAPI

/**
 * Created by Marcelo on 09/07/2018.
 */
class CategoryRepository(application: Application) {
    var mCategoryDao: CategoryDao? = null
    var AmaiAPI: AmaiAPI? = null

    init {
        val db = KenaryDatabase.getDatabase(application)
        mCategoryDao = db?.categoryDao()
    }

    fun getAll(): LiveData<List<Category>> = mCategoryDao!!.getAllCategory()

    fun getAllbyId(id: Int): LiveData<Category> = mCategoryDao!!.getCategoryById(id)

    fun insert(category: Category){
        CompositeDisposable().add(Observable.fromCallable { mCategoryDao!!.insert(category) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
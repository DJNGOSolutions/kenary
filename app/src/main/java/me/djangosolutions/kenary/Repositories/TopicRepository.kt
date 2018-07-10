package me.djangosolutions.kenary.Repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.djangosolutions.kenary.Database.Daos.TopicDao
import me.djangosolutions.kenary.Database.KenaryDatabase
import me.djangosolutions.kenary.Entity.Topic
import me.djangosolutions.kenary.Webserver.AmaiAPI

class TopicRepository (application: Application){
    var mTopicDao: TopicDao? = null
    var AmaiAPI: AmaiAPI? = null

    init{
        val db = KenaryDatabase.getDatabase(application)
        mTopicDao = db?.topicDao()
    }

    fun getAll(): LiveData<List<Topic>> = mTopicDao!!.getAllTopic()

    fun getAllbyId(id: Int): LiveData<Topic> = mTopicDao!!.getTopicById(id)

    fun insert(topic: Topic){
        CompositeDisposable().add(Observable.fromCallable { mTopicDao!!.insert(topic)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
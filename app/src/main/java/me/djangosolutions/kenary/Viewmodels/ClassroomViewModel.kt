package me.djangosolutions.kenary.Viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import me.djangosolutions.kenary.Entity.ClassroomEntity
import me.djangosolutions.kenary.Entity.UserEntity
import me.djangosolutions.kenary.Repositories.ClassroomRepository
import me.djangosolutions.kenary.Repositories.UserRepository

/**
 * Created by Marcelo on 12/07/2018.
 */
class ClassroomViewModel (application: Application): AndroidViewModel(application) {
    internal var mRepository: ClassroomRepository? = null

    init {
        mRepository = ClassroomRepository(application)
    }

    fun getAll(): LiveData<List<ClassroomEntity>> = mRepository!!.getAll()

    fun getAllbyId(id: Int): LiveData<ClassroomEntity> = mRepository!!.getClassRoomByID(id)

    fun insert(classroomEntity: ClassroomEntity) = mRepository!!.insert(classroomEntity)

}
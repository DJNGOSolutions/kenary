package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.UserEntity

/**
 * Created by Marcelo on 12/07/2018.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAllUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user_table WHERE idUser = :IdUser")
    fun getUserById(idUser: Int): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userEntity: UserEntity)

    @Query("DELETE FROM user_table")
    fun deleteAll()
}
package me.djangosolutions.kenary.Database.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.djangosolutions.kenary.Entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user_table Where idUser = :idUser")
    fun getUserById(idUser: Int)  : LiveData<User>

    //@Query("UPDATE table_user set column")

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()
}
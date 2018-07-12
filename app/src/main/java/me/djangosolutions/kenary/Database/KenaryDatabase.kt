package me.djangosolutions.kenary.Database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import me.djangosolutions.kenary.Database.Daos.*
import me.djangosolutions.kenary.Entity.*
import java.util.ArrayList

@Database(entities = [(Class::class), (Tutorial::class),(ClassroomEntity::class), (CategoryEntity::class), (TutorialEntity::class), (UserEntity::class)], version = 7)
abstract class KenaryDatabase: RoomDatabase(){

    private class PopulateDbAsync internal constructor(db: KenaryDatabase): AsyncTask<Void, Void, Void>(){
        private val mClassDao: ClassDao = db.classDao()
        private val mTutorialDao: TutorialDao = db.tutorialDao()
        private val mClassDaoA: ClassroomDao = db.classroomDao()
        private val mTutorialDaoA: TutorialDaoA = db.tutorialDaoA()
        private val mUserDao: UserDao = db.userDao()

        override fun doInBackground(vararg params: Void): Void? {
            mClassDao.deleteAll()
            mTutorialDao.deleteAll()
            mClassDaoA.deleteAll()
            mTutorialDaoA.deleteAll()
            mUserDao.deleteAll()
            val list = ArrayList<Class>()
            list.add(Class(1, 0, 0, 7,"Fluídos"))
            list.add(Class(2,0,0, 10 , "Cálculo"))
            list.add(Class(3,0,0, 2 , "Ciencias de los materiales"))
            list.add(Class(4, 0,0, 5, "Contaduría"))
            val listy = ArrayList<Tutorial>()
            listy.add(Tutorial(1,0,0, "Derivadas"))
            listy.add(Tutorial(2, 0, 0, "Ecuaciones Diferenciales"))
            listy.add(Tutorial(3, 0, 0, "Economía"))
            for (classy in list) mClassDao.insert(classy)
            for (tutty in listy) mTutorialDao.insert(tutty)
            val lista = ArrayList<ClassroomEntity>()
            lista.add(ClassroomEntity(0, "Matematicas", "Matematicas", "Nelson Castro", "https://bit.ly/2Mr7J6K", "Polinomio de taylor", "UCA", "9:00 AM","24/07/18", "6.77$"))
            lista.add(ClassroomEntity(1, "Matematicas", "Matematicas", "Nelson Castro", "https://bit.ly/2Mr7J6K", "Integración continua", "UCA", "11:00 AM","24/07/18", "6.77$"))
            lista.add(ClassroomEntity(2, "Fisica", "Fisica", "Nelson Castro", "https://bit.ly/2Mr7J6K", "Programación Orientada a Objetos", "UCA", "1:30 PM","24/07/18", "6.77$"))
            lista.add(ClassroomEntity(3, "Matematicas", "Matematicas", "Nelson Castro", "https://bit.ly/2Mr7J6K", "Programación Orientada a Objetos", "UCA", "9:00 AM","24/07/18", "6.77$"))
            val listonga= ArrayList<TutorialEntity>()
            listonga.add(TutorialEntity(0, "Matematicas", "Matematicas", "Nelson Castro", "https://bit.ly/2Mr7J6K", "Polinomio de taylor", "UCA", "9:00 AM","24/07/18", "6.77$"))
            listonga.add(TutorialEntity(1, "Matematicas", "Matematicas", "Nelson Castro", "https://bit.ly/2Mr7J6K", "Integración continua", "UCA", "11:00 AM","24/07/18", "6.77$"))
            listonga.add(TutorialEntity(2, "Fisica", "Fisica", "Nelson Castro", "https://bit.ly/2Mr7J6K", "Programación Orientada a Objetos", "UCA", "1:30 PM","24/07/18", "6.77$"))
            listonga.add(TutorialEntity(3, "Matematicas", "Matematicas", "Nelson Castro", "https://bit.ly/2Mr7J6K", "Programación Orientada a Objetos", "UCA", "9:00 AM","24/07/18", "6.77$"))
            for (tasty in lista) mClassDaoA.insert(tasty)
            for (masty in listonga) mTutorialDaoA.insert(masty)
            mUserDao.insert(UserEntity(0, 0, 2, "https://bit.ly/2Mr7J6K", "nelsoncaastro", "Nelson Castro", "UCA", "roothashed"))
            return null
        }
    }

    abstract fun classDao(): ClassDao
    abstract fun tutorialDao(): TutorialDao
    abstract fun userDao(): UserDao
    abstract fun tutorialDaoA(): TutorialDaoA
    abstract fun classroomDao(): ClassroomDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        private var INSTANCE: KenaryDatabase? = null

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }

        fun getDatabase(context: Context): KenaryDatabase? {
            if (INSTANCE == null){
                synchronized(KenaryDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            KenaryDatabase::class.java, "database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                }
            }
            return INSTANCE
        }

        fun nukeInstance() {
            INSTANCE = null
        }
    }
}
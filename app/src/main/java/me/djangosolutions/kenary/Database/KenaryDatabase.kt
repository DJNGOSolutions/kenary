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

@Database(entities = [(AcademicLevel::class), (Assistance::class), (Classroom::class), (Gender::class), (Phone::class), (Place::class), (Role::class), (Session::class), (Subject::class), (Topic::class), (TypePayment::class), (TypeSession::class), (User::class)], version = 3)
abstract class KenaryDatabase: RoomDatabase(){

    private class PopulateDbAsync internal constructor(db: KenaryDatabase): AsyncTask<Void, Void, Void>(){
        private val mClassroomDao: ClassroomDao = db.classroomDao()
        private val mTutorialDao: SessionDao = db.sessionDao()

        override fun doInBackground(vararg params: Void): Void? {
            //mClassroomDao.deleteAll()
            //mTutorialDao.deleteAll()
            //val list = ArrayList<Classroom>()
            //list.add(Classroom(1, 0, 0))
            //list.add(Classroom(2,0,0))
            //list.add(Classroom(3,0,0))
            //list.add(Classroom(4, 0,0))
            //val listy = ArrayList<Session>()
            //listy.add(Session(1,0,0, 0))
            //listy.add(Session(2, 0, 0, 0))
            //listy.add(Session(3, 0, 0, 0))
            //for (classy in list) mClassroomDao.insert(classy)
            //for (tutty in listy) mTutorialDao.insert(tutty)
            return null
        }
    }

    abstract fun academicLevelDao(): AcademicLevelDao
    abstract fun assistanceDao(): AssistanceDao
    abstract fun categoryDao(): CategoryDao
    abstract fun classroomDao(): ClassroomDao
    abstract fun genderDao(): GenderDao
    abstract fun placeDao(): PlaceDao
    abstract fun phoneDao(): PhoneDao
    abstract fun roleDao(): RoleDao
    abstract fun sessionDao(): SessionDao
    abstract fun subjectDao(): SubjectDao
    abstract fun subscriptionDao(): SubscriptionDao
    abstract fun topicDao(): TopicDao
    abstract fun typePaymentDao(): TypePaymentDao
    abstract fun typeSessionDao(): TypeSessionDao
    abstract fun userClassroomDao(): UserClassroomDao
    abstract fun userDao(): UserDao

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
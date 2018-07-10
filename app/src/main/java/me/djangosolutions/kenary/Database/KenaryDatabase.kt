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

@Database(entities = [(AcademicLevel::class), (Assistance::class),(Class::class), (Category::class), (Classroom::class), (Gender::class), (Phone::class), (Place::class), (Role::class), (Session::class), (Subject::class), (Subscription::class), (Topic::class), (TypePayment::class), (Tutorial::class), (TypeSession::class), (User::class), (UserClassroom::class)], version = 4)
abstract class KenaryDatabase: RoomDatabase(){

    private class PopulateDbAsync internal constructor(db: KenaryDatabase): AsyncTask<Void, Void, Void>(){
        private val mClassDao: ClassDao = db.classDao()
        private val mTutorialDao: TutorialDao = db.tutorialDao()

        override fun doInBackground(vararg params: Void): Void? {
            mClassDao.deleteAll()
            mTutorialDao.deleteAll()
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
    abstract fun classDao(): ClassDao
    abstract fun classroomDao(): ClassroomDao
    abstract fun genderDao(): GenderDao
    abstract fun placeDao(): PlaceDao
    abstract fun phoneDao(): PhoneDao
    abstract fun roleDao(): RoleDao
    abstract fun sessionDao(): SessionDao
    abstract fun subjectDao(): SubjectDao
    abstract fun subscriptionDao(): SubscriptionDao
    abstract fun topicDao(): TopicDao
    abstract fun tutorialDao(): TutorialDao
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
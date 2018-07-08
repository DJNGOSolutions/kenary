package me.djangosolutions.kenary.Database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import me.djangosolutions.kenary.Database.Daos.*
import me.djangosolutions.kenary.Entity.*
import me.djangosolutions.kenary.Models.ModelClass
import me.djangosolutions.kenary.Models.ModelTuto
import java.util.ArrayList

@Database(entities = [(AcademicLevel::class), (Assistance::class), (City::class), (Class::class), (Gender::class), (Institution::class), (Phone::class), (Place::class), (Product::class), (Rate::class), (Role::class), (Session::class), (State::class), (Study::class), (Subject::class), (SubjectTopic::class), (Topic::class), (Tutorial::class), (TypePayment::class), (TypeSession::class), (User::class)], version = 3)
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
            return null
        }
    }

    abstract fun academicLevelDao(): AcademicLevelDao
    abstract fun assistanceDao(): AssistanceDao
    abstract fun cityDao(): CityDao
    abstract fun classDao(): ClassDao
    abstract fun genderDao(): GenderDao
    abstract fun institutionDao(): InstitutionDao
    abstract fun phoneDao(): PhoneDao
    abstract fun rateDao(): PlaceDao
    abstract fun roleDao(): RoleDao
    abstract fun sessionDao(): SessionDao
    abstract fun stateDao(): StateDao
    abstract fun studyDao(): StudyDao
    abstract fun subjectDao(): SubjectDao
    abstract fun subjectTopicDao(): SubjectTopicDao
    abstract fun topicDao(): TopicDao
    abstract fun tutorialDao(): TutorialDao
    abstract fun typePaymentDao(): TypePaymentDao
    abstract fun typeSessionDao(): TypeSessionDao
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
package me.djangosolutions.kenary.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import me.djangosolutions.kenary.Database.Daos.*
import me.djangosolutions.kenary.Entity.*

@Database(entities = [(AcademicLevel::class), (Assistance::class), (City::class), (Class::class), (Gender::class), (Institution::class), (Phone::class), (Place::class), (Product::class), (Rate::class), (Role::class), (Session::class), (State::class), (Study::class), (Subject::class), (SubjectTopic::class), (Topic::class), (Tutorial::class), (TypePayment::class), (TypeSession::class), (User::class)], version = 2)
abstract class KenaryDatabase: RoomDatabase(){

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

        fun getDatabase(context: Context): KenaryDatabase? {
            if (INSTANCE == null){
                synchronized(KenaryDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            KenaryDatabase::class.java, "database")
                            .fallbackToDestructiveMigration()
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
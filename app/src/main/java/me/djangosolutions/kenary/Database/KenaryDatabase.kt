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

@Database(entities = [(ClassroomEntity::class), (CategoryEntity::class), (TutorialEntity::class), (UserEntity::class)], version = 9)
abstract class KenaryDatabase: RoomDatabase(){

    private class PopulateDbAsync internal constructor(db: KenaryDatabase): AsyncTask<Void, Void, Void>(){
        private val mClassDaoA: ClassroomDao = db.classroomDao()
        private val mTutorialDaoA: TutorialDaoA = db.tutorialDaoA()
        private val mUserDao: UserDao = db.userDao()

        override fun doInBackground(vararg params: Void): Void? {
            mClassDaoA.deleteAll()
            mTutorialDaoA.deleteAll()
            mUserDao.deleteAll()
            val lista = ArrayList<ClassroomEntity>()
            lista.add(ClassroomEntity(0, "Matematicas", "Matematicas", "Rafael Perez", "https://dspncdn.com/a1/media/692x/fc/c7/67/fcc76765091aed9c44ad68d48db06c83.jpg", "Ecuaciones Diferenciales aplicadas", "UCA", "9:00 AM c/d","24/07/18 - 04/08/18", "20$"))
            lista.add(ClassroomEntity(1, "Bases de datos", "Bases de datos", "Nelson Obdulio", "https://image.freepik.com/vector-gratis/diseno-del-lenguaje-de-programacion_24908-7968.jpg", "Bases de datos no relacionales", "UCA", "11:00 AM c/d","24/07/18 - 24/09/18", "20$"))
            lista.add(ClassroomEntity(2, "Probabilidad y estadistica", "Programación web", "Gabriel Garcia", "https://image.freepik.com/vector-gratis/proceso-de-desarrollo-de-aplicaciones-moviles_1200-441.jpg", "Progressive Web Frameworks", "UCA", "1:30 PM c/d","24/07/18 - 01/08/18", "6.77$"))
            lista.add(ClassroomEntity(3, "Fisica", "Fisica", "Javier Parada", "https://4.bp.blogspot.com/-d9jmYTqXzLQ/V6Cnnpgel1I/AAAAAAAAAps/ZNM1o82BwTQ0BpHuysCvSIly9jEzjgGSACLcB/s320/qu%25C3%25ADmica.jpg", "Fisica Cinematica", "UCA", "9:00 AM c/d","24/07/18 - 17/08/18", "17$"))
            val listonga= ArrayList<TutorialEntity>()
            listonga.add(TutorialEntity(0, "Matematicas", "Matematicas", "Rafael Perez", "Rodrigo Alvarenga","https://dspncdn.com/a1/media/692x/fc/c7/67/fcc76765091aed9c44ad68d48db06c83.jpg", "Polinomio de taylor", "UCA", "9:00 AM","24/07/18", "6.77$"))
            listonga.add(TutorialEntity(1, "Bases de datos", "Bases de datos", "Nelson Obdulio", "Marcelo Martinez","https://image.freepik.com/vector-gratis/diseno-del-lenguaje-de-programacion_24908-7968.jpg", "Integración continua", "UCA", "11:00 AM","24/07/18", "6.77$"))
            listonga.add(TutorialEntity(2, "Probabilidad y estadistica", "Probabilidad y estadistica", "Gabriel Garcia", "Nelson Castro","https://image.freepik.com/vector-gratis/proceso-de-desarrollo-de-aplicaciones-moviles_1200-441.jpg", "Curva normal", "UCA", "1:30 PM","24/07/18", "6.77$"))
            listonga.add(TutorialEntity(3, "Fisica","Fisica", "Javier Parada", "Arturo Salgado","https://4.bp.blogspot.com/-d9jmYTqXzLQ/V6Cnnpgel1I/AAAAAAAAAps/ZNM1o82BwTQ0BpHuysCvSIly9jEzjgGSACLcB/s320/qu%25C3%25ADmica.jpg", "Tiro parabolico", "UCA", "9:00 AM","24/07/18", "6.77$"))
            for (tasty in lista) mClassDaoA.insert(tasty)
            for (masty in listonga) mTutorialDaoA.insert(masty)
            mUserDao.insert(UserEntity(0, 0, 2, "https://bit.ly/2Mr7J6K", "nelsoncaastro", "Nelson Castro", "UCA", "roothashed"))
            return null
        }
    }

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
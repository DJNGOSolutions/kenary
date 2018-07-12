package me.djangosolutions.kenary.util
import android.view.Display
import retrofit2.Call
import retrofit2.http.*


object Model{
    data class User( val Id: Int?, val UserPhoto: String, val UserName: String,val UserInstitution: String,
                     val idGoogle : Integer,var role: String)
    data class Subject( val ID: Int, val IdCategory: Int, val Subject: String )
    data class Category( val ID: Int, val IdSubject: Int, val TopicName: String )
    data class AcademicLevels( val id: String,val AcademicLevel : String)
    data class Sessions(val UserEmail:String, val SessionDate:String)
    data class Classrooms(val Id:Int, val UserName: String, val TopicName: String,
                          val IdSubject: Int, val SubjectName: String)
}

interface AmaiService {
    @POST("login")
    fun login(@Field("username") username: String, @Field("pass") password: String): Call<String>

    @POST("/find/user")
    fun getUser(@Field("token") token: String): Call<Model.User>

    @GET("/subjects")
    fun getSubjects() : Call<List<Model.Subject>>

    @GET("/category")
    fun getCategories() : Call<List<Model.Category>>

    @GET("/academics")
    fun getAcademicLevels() : Call<List<Model.AcademicLevels>>

    @GET("/users/sessions")
    fun getSessions():Call<List<Model.Sessions>>

    @GET("/classrooms")
    fun getClassrooms():Call<List<Model.Classrooms>>
}
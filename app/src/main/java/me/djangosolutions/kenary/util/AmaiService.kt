package me.djangosolutions.kenary.util
import retrofit2.Call
import retrofit2.http.*


object Model{
    data class User( val ID: Int?, val UserName: String, val UserAge: String, val Gender: String,
                     val UserEmail: String,val AcademicLevel: String, var role: String)
    data class Subject( val ID: Int, val IdCategory: Int, val Subject: String )
    data class Category( val ID: Int, val IdSubject: Int, val TopicName: String )
    data class AcademicLevels( val id: String,val AcademicLevel : String)

}

interface AmaiService {
    @POST("login")
    fun login(@Field("email") email: String, @Field("pass") password: String): Call<String>

    @POST("/find/user")
    fun getUser(@Field("email") email: String): Call<Model.User>

    @GET("/subjects")
    fun getSubjects() : Call<List<Model.Subject>>

    @GET("/category")
    fun getCategories() : Call<List<Model.Category>>

    @GET("/academics")
    fun getAcademicLevels() : Call<List<Model.AcademicLevels>>

}
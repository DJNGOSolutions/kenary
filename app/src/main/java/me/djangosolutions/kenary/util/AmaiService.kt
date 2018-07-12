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
    data class session( val idCategory: Int, val idSubject: Int, val idUser: Int, val tutoryTheme: String,val tutoryTime: string,
                       val tutoryDate: String, val tutoryPrice: String, val idusertutor: Int, 
                       val place: String, val idUserCustomer: Int, val id : Int)
}

interface AmaiService {
    @POST("login")
    fun login(@Field("username") username: String, @Field("pass") password: String): Call<String>

    @POST("/find/user")
    fun getUser(@Field("token") token: String): Call<Model.User>
    
      @POST("/create/session")
    fun createSession(@Field("idCategory") idCategory: String, @Field("idSubject") idSubject: Int,
               @Field("idUser") idUSer: int, @Field("tutoryTHeme") tutoryTheme: String,
                    @Field("tutoryTheme") tutoryTheme: String, @Field("tutoryDate"),
                     @Field("tutoryPrice") tutoryPrice : Int,
                     @Field("iuserTutor") idUserTutor: Int, @Field("idUserCustomer") idUserCustmoer: String,
                      @Field("id") id): Call<Model.session>
    
      @POST("/join/session")
    fun joinSession( @Field("idUserCustomer") idUserCustmoer: String, @Field("id") id): Call<Model.session>

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

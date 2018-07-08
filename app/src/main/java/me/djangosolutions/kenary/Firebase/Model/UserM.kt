package me.djangosolutions.kenary.Firebase.Model

data class UserM(val diplayName:String,
                 val bio:String?,
                 val email:String,
                 val profilePicPath:String?){
    constructor():this("","","",null)
}
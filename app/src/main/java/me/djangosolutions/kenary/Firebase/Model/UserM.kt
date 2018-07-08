package me.djangosolutions.kenary.Firebase.Model

data class UserM(val diplayName:String,
                 val institution:String?,
                 val email:String,
                 val profilePicPath:String?){
    constructor():this("","","",null)
}
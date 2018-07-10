package me.djangosolutions.kenary.Firebase.Model

import java.util.*

object MessageType{
    const val TEXT = "TEXT"
    const val OMAGE = "IMAGE"
}

interface Message {
    val time: Date
    val senderId:String
    val type:String
}
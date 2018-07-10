package me.djangosolutions.kenary.Firebase.Model

data class ChatChannel(val userIds:MutableList<String>) {
    constructor():this(mutableListOf())
}
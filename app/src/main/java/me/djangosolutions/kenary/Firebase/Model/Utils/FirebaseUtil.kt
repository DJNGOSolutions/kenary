package me.djangosolutions.kenary.Firebase.Model.Utils

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import me.djangosolutions.kenary.Firebase.Model.UserM

object FirebaseUtil {
    private val firestoreInstance:FirebaseFirestore by lazy{FirebaseFirestore.getInstance()}
    private val currentUserDocRef:DocumentReference
        get() = firestoreInstance.document("users/${FirebaseAuth.getInstance().uid
                ?: throw NullPointerException("UID is null.")}")
    fun initCurrentUserIfFirstTime(onComplete: () -> Unit){
        currentUserDocRef.get().addOnSuccessListener { documentSnapshot ->
            if(!documentSnapshot.exists()){
                val newUser = UserM(FirebaseAuth.getInstance().currentUser?.displayName ?: "",
                        "",
                        FirebaseAuth.getInstance().currentUser?.email ?: "",
                        null)
                currentUserDocRef.set(newUser).addOnSuccessListener {
                    onComplete()
                }
            }else
                onComplete()
        }
    }

    fun updateCurrentUser(name:String ="",bio:String="",email:String="", profilePicPath:String? = null){
        val userFieldMap = mutableMapOf<String,Any>()
        if(name.isNotBlank()) userFieldMap["name"] = name
        if(bio.isNotBlank()) userFieldMap["bio"] = bio
        if(email.isNotBlank()) userFieldMap["email"] = email
        if(profilePicPath != null)
            userFieldMap["profilePicPath"] = profilePicPath
        currentUserDocRef.update(userFieldMap)
    }

    fun getCurrentUser(onComplete: (UserM) -> Unit){
        currentUserDocRef.get()
                .addOnSuccessListener {
                    onComplete(it.toObject(UserM::class.java)!!)
                }
    }
}

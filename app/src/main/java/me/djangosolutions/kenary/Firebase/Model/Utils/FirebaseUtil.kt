package me.djangosolutions.kenary.Firebase.Model.Utils

import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.xwray.groupie.kotlinandroidextensions.Item
import me.djangosolutions.kenary.Firebase.Model.UserM

object FirebaseUtil {
    private val firestoreInstance:FirebaseFirestore by lazy{FirebaseFirestore.getInstance()}
    private val currentUserDocRef:DocumentReference
        get() = firestoreInstance.document("users/${FirebaseAuth.getInstance().currentUser?.uid
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

    fun updateCurrentUser(name:String ="",institution:String="",email:String="", profilePicPath:String? = null){
        val userFieldMap = mutableMapOf<String,Any>()
        if(name.isNotBlank()) userFieldMap["name"] = name
        if(institution.isNotBlank()) userFieldMap["bio"] = institution
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

    fun addUserListener(context:Context, onListen: (List<Item>) -> Unit):ListenerRegistration{
        return firestoreInstance.collection("users")
                .addSnapshotListener{querySnapshot, firebaseFirestoreException ->
                    if(firebaseFirestoreException != null) {
                        Log.e("FIRESTORE", "User listener error.", firebaseFirestoreException)
                        return@addSnapshotListener
                    }
                    val items = mutableListOf<Item>()
                    querySnapshot?.documents?.forEach{
                        if(it.id != FirebaseAuth.getInstance().currentUser?.uid){
                            //TODO
                            //items.add(PersonItem(it.toObject(User::class.java),it.id,context))
                        }
                    }
                    onListen(items)
                }
    }

    fun  removeListener(registration: ListenerRegistration) = registration.remove()
}

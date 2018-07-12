package me.djangosolutions.kenary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_up.*
import me.djangosolutions.kenary.Firebase.Model.Utils.FirebaseUtil
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class SplashActivity:AppCompatActivity(){
    lateinit var mAuth: FirebaseAuth
    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = mAuth.currentUser
        updateUI(currentUser)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        mAuth = FirebaseAuth.getInstance()
    }
    private fun updateUI(user: FirebaseUser?){
        if(user != null){
            if(mAuth.currentUser!!.isEmailVerified){
                FirebaseUtil.initCurrentUserIfFirstTime {
                    startActivity(intentFor<MainActivity>().newTask().clearTask())
                }
            }else{
                //Toast.makeText(this,"Email not verified, please check your email.",Toast.LENGTH_SHORT).show()
                mAuth.signOut()
                startActivity(intentFor<LoginActivity>().newTask().clearTask())
            }
        }else{
            startActivity(intentFor<LoginActivity>().newTask().clearTask())
        }
    }
}
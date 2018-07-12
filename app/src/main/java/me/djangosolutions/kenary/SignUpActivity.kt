package me.djangosolutions.kenary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import me.djangosolutions.kenary.Firebase.Model.Utils.FirebaseUtil
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class SignUpActivity : AppCompatActivity() {
    lateinit var mAuth:FirebaseAuth

    override fun onStart() {
        super.onStart()
        val currentUser:FirebaseUser? = mAuth.currentUser
        updateUI(currentUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mAuth = FirebaseAuth.getInstance()
        sign_up.setOnClickListener {
            createAccount(username_signup.text.toString(),password_signup.text.toString())
        }
    }

    fun createAccount(email:String, password:String){
        Log.d(LoginActivity.TAG2, "createAccount" + email)
        if(!validateForm()){
            return
        }
        val progressDialog = indeterminateProgressDialog("")
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        senddEmailVerification()
                        Log.d(LoginActivity.TAG2,"createUserWithEmailAndPassword: success")
                        val user:FirebaseUser = mAuth.currentUser!!
                        updateUI(null)
                    }else{
                        Log.w(LoginActivity.TAG2,"createUserWithEmailAndPassword: failure",task.exception)
                        Toast.makeText(this,"Authentication failed",Toast.LENGTH_SHORT).show()
                        //Snackbar.make(View(this), "Authentication failed.", Snackbar.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                    progressDialog.dismiss()
                }
    }

    fun validateForm():Boolean{
        var valid:Boolean = true
        val email:String = username_signup.text.toString()
        if(TextUtils.isEmpty(email)){
            username_signup.error = "Required."
            valid = false
        }else{
            username_signup.error = null
        }
        val password:String = password_signup.text.toString()
        if(TextUtils.isEmpty(password)){
            password_signup.error = "Required"
            valid = false
        }else{
            password_signup.error = null
        }
        return valid
    }

    private fun senddEmailVerification(){
        val user:FirebaseUser = mAuth.currentUser!!
        user.sendEmailVerification()
                .addOnCompleteListener {task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Verification email sent to ${user.email}",Toast.LENGTH_SHORT).show()
                        //Snackbar.make(View(this),"Verification email sent to: ${user.email}",Snackbar.LENGTH_SHORT).show()
                    }else{
                        Log.e(LoginActivity.TAG2,"sendEmailVerification",task.exception)
                        Toast.makeText(this,"Failed to sent verification email.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }

    private fun updateUI(user: FirebaseUser?){
        if(user != null){
            FirebaseUtil.initCurrentUserIfFirstTime {
                startActivity(intentFor<LoginActivity>().newTask().clearTask())
            }
        }else{
            //TODO:?
        }
    }
}

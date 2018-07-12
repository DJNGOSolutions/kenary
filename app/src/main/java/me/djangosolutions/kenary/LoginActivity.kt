package me.djangosolutions.kenary

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import me.djangosolutions.kenary.Firebase.Model.Utils.FirebaseUtil
import org.jetbrains.anko.*
import org.jetbrains.anko.design.longSnackbar

class LoginActivity : AppCompatActivity() {
    companion object {
        val RC_SIGN_IN:Int = 9001
        val RC_SIGN_IN2:Int = 9002
        val TAG:String = "GoogleActivity"
        val TAG2:String = "EmailPassword"
    }

    private val signInProviders =
            listOf(AuthUI.IdpConfig.EmailBuilder()
                    .setAllowNewAccounts(true)
                    .setRequireName(true)
                    .build())

    lateinit var mAuth:FirebaseAuth
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var gso:GoogleSignInOptions
    //lateinit var mStatusTextView:TextView
    //lateinit var mDetailTextView: TextView

    override fun onStart() {
        super.onStart()
        val currentUser:FirebaseUser? = mAuth.currentUser
        updateUI(currentUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //mStatusTextView = findViewById(R.id.status)
        //mDetailTextView = findViewById(R.id.detail)
        val signIn = findViewById<View>(R.id.sign_in_button) as SignInButton
        //val signOut = findViewById<View>(R.id.sign_out_button) as Button
        //val revokeAccess = findViewById<View>(R.id.disconnect_button)
        signIn.setSize(SignInButton.SIZE_WIDE)

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)
        mAuth = FirebaseAuth.getInstance()
        signIn.setOnClickListener {
            v: View? -> signInGoogle()
        }
/*        signOut.setOnClickListener {
            v: View? ->  signOut()
        }
        revokeAccess.setOnClickListener {
            v: View? ->  revokeAcces()
        }*/
        account_sign_in.setOnClickListener {
            sigIn(username.text.toString(),password.text.toString())
        }
        sign_up_login.setOnClickListener {
            startActivity(intentFor<SignUpActivity>())
           /* val intent = AuthUI.getInstance().createSignInIntentBuilder()
                    .setAvailableProviders(signInProviders)
                    .build()
            startActivityForResult(intent, RC_SIGN_IN2)*/
        }
    }

    fun createAccount(email:String, password:String){
        Log.d(TAG2, "createAccount" + email)
        if(!validateForm()){
            return
        }
        val progressDialog = indeterminateProgressDialog("")
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Log.d(TAG2,"createUserWithEmailAndPassword: success")
                        val user:FirebaseUser = mAuth.currentUser!!
                        updateUI(user)
                    }else{
                        Log.w(TAG2,"createUserWithEmailAndPassword: failure",task.exception)
                        Snackbar.make(View(this), "Authentication failed.",Snackbar.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                    progressDialog.dismiss()
                }
    }

    fun validateForm():Boolean{
        var valid:Boolean = true
        val email:String = username.text.toString()
        if(TextUtils.isEmpty(email)){
            username.error = "Required."
            valid = false
        }else{
            username.error = null
        }
        val passwordd:String = password.text.toString()
        if(TextUtils.isEmpty(passwordd)){
            password.error = "Required"
            valid = false
        }else{
            password.error = null
        }
        return valid
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN){
            val task:Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account:GoogleSignInAccount = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            }catch (e:ApiException){
                Log.w(TAG,"Google sign in failed",e)
                updateUI(null)
            }
        }
        if (requestCode == RC_SIGN_IN2){
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK){
                val progressDialog = indeterminateProgressDialog("Setting up your account")
                FirebaseUtil.initCurrentUserIfFirstTime {
                    startActivity(intentFor<MainActivity>().newTask().clearTask())
                    progressDialog.dismiss()
                }

            }
            else if (resultCode == Activity.RESULT_CANCELED){
                if (response == null) return

                when(response.error?.errorCode){
                    ErrorCodes.NO_NETWORK ->
                        longSnackbar(View(this), "No Network")
                    ErrorCodes.UNKNOWN_ERROR ->
                        longSnackbar(View(this), "Unknown error")
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct:GoogleSignInAccount){
        Log.d(TAG,"firebaseAuthWithGoogle: "+acct.id)

        val credential:AuthCredential = GoogleAuthProvider.getCredential(acct.idToken,null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        Log.d(TAG,"SignInWithCredential:Success")
                        val user:FirebaseUser = mAuth.currentUser!!
                        updateUI(user)
                    }else{
                        Log.w(TAG,"signInWithCredential:Failure",it.exception)
                        Snackbar.make(View(this),"Authentication Failed.",Snackbar.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
    }

    private fun sigIn(email:String,password: String){
        Log.d(TAG2, "singIn:$email")
        if(!validateForm()){
            return
        }
        val progressDialog = indeterminateProgressDialog("")

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val user:FirebaseUser = mAuth.currentUser!!
                        Log.d(TAG2,"SigInWithEmail: Success")
                        if(mAuth.currentUser!!.isEmailVerified){
                            updateUI(user)
                        }else{
                            Toast.makeText(this, "Email is not verified, please check your email.",Toast.LENGTH_SHORT).show()
                            mAuth.signOut()
                        }

                    }else{
                        Log.w(TAG2,"SignInWithEmail: Failure",task.exception)
                        //Snackbar.make(View(this),"Authentication Failed.",Snackbar.LENGTH_SHORT).show()
                        Toast.makeText(this,"Authentication failed.",Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                    if(!task.isSuccessful){
                        TODO("JUST SET THE STATUS TO THE VIEW. ACTUALLY THIS IS CRINGE")
                    }
                    progressDialog.dismiss()
                }
    }

    private fun sendEmailVerification(){
        val user:FirebaseUser = mAuth.currentUser!!
        user.sendEmailVerification()
                .addOnCompleteListener {task ->
                    if(task.isSuccessful){
                        Snackbar.make(View(this),"Verification email sent to: ${user.email}",Snackbar.LENGTH_SHORT).show()
                    }else{
                        Log.e(TAG2,"sendEmailVerification",task.exception)
                        Toast.makeText(this,"Failed to sent verification email.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }

    private fun signInGoogle(){
        val signInIntent:Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun SignOut(){
        mAuth.signOut()
        updateUI(null)
    }

    private fun signOutGoogle(){
        //Firebase sign out
        mAuth.signOut()
        //Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener {
            updateUI(null)
        }
    }

    private fun revokeAcces(){
        //Firebase sign out
        mAuth.signOut()
        //Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener {
            updateUI(null)
        }
    }

    private fun updateUI(user: FirebaseUser?){
        if(user != null){
            // mStatusTextView.text = getString(R.string.google_status_fmt,user.email)
            //mDetailTextView.text = getString(R.string.firebase_status_fmt,user.uid)
            //findViewById<SignInButton>(R.id.sign_in_button).visibility = View.GONE
            //findViewById<LinearLayout>(R.id.sign_out_and_disconnect).visibility = View.VISIBLE
            if(mAuth.currentUser!!.isEmailVerified){
                FirebaseUtil.initCurrentUserIfFirstTime {
                    startActivity(intentFor<MainActivity>().newTask().clearTask())
                }
            }else{
                mAuth.signOut()
            }

        }else{
            //  mStatusTextView.text = getString(R.string.signed_out)
            // mDetailTextView.text = null
            // findViewById<SignInButton>(R.id.sign_in_button).visibility = View.VISIBLE
            //findViewById<LinearLayout>(R.id.sign_out_and_disconnect).visibility = View.GONE
        }
    }
}
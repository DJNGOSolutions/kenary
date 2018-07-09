package me.djangosolutions.kenary.Fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import me.djangosolutions.kenary.glide.GlideApp
import com.google.android.gms.signin.SignIn
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.profile.*
import kotlinx.android.synthetic.main.profile.view.*
import me.djangosolutions.kenary.Adapters.TutoRvAdapter
import me.djangosolutions.kenary.Firebase.Model.Utils.FirebaseUtil
import me.djangosolutions.kenary.Firebase.Model.Utils.StorageUtil
import me.djangosolutions.kenary.Fragments.PFragHome.PFragHome
import me.djangosolutions.kenary.Fragments.PFragHome.PFragHomeContent
import me.djangosolutions.kenary.LoginActivity
import me.djangosolutions.kenary.R
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.newTask
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast
import java.io.ByteArrayOutputStream

class PFragProfile : Fragment() {
    private val RC_SELECT_IMAGE = 2
    private lateinit var selectedImageBytes:ByteArray
    private var pictureJustChanged =false
    private var loginActivity: LoginActivity? = null
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.profile, container, false)

        view.apply {
            profile_image.setOnClickListener {
                val intent = Intent().apply {
                    type = "image/*"
                    action = Intent.ACTION_GET_CONTENT
                    putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg","image/png"))
                }
                startActivityForResult(Intent.createChooser(intent,"Select image"),RC_SELECT_IMAGE)
            }
            save_profile_button.setOnClickListener {
                if(::selectedImageBytes.isInitialized){
                    StorageUtil.uploadProfilePhoto(selectedImageBytes){imagePath ->
                        FirebaseUtil.updateCurrentUser(display_name.text.toString(),
                                institution_text.text.toString(),
                                email_text.text.toString(),
                                imagePath
                                )
                    }
                }else{
                    FirebaseUtil.updateCurrentUser(display_name.text.toString(),
                            institution_text.text.toString(),
                            email_text.text.toString(),
                            null
                    )
                }
                toast("Saving")
            }
            sign_out_profile.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                AuthUI.getInstance()
                        .signOut(this@PFragProfile.context!!)
                        .addOnCompleteListener {
                            startActivity(intentFor<LoginActivity>().newTask().clearTask())
                        }
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceFragment(PFragHomeContent())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == RC_SELECT_IMAGE && resultCode == Activity.RESULT_OK &&
                data != null && data.data != null){
            val selectedImagePath = data.data
            val selectedImageBmp = MediaStore.Images.Media
                    .getBitmap(activity?.contentResolver,selectedImagePath)
            val outputStream = ByteArrayOutputStream()
            selectedImageBmp.compress(Bitmap.CompressFormat.JPEG,90,outputStream)
            selectedImageBytes = outputStream.toByteArray()
            GlideApp.with(this)
                    .load(selectedImageBytes)
                    .into(profile_image)
            pictureJustChanged = true
        }
    }

    override fun onStart() {
        super.onStart()
        FirebaseUtil.getCurrentUser { userM ->
            if(this@PFragProfile.isVisible){
                display_name.setText(userM.diplayName)
                email_text.setText(userM.email)
                institution_text.setText(userM.institution)
                if(!pictureJustChanged && userM.profilePicPath != null){
                    GlideApp.with(this)
                            .load(StorageUtil.pathToReference(userM.profilePicPath))
                            .placeholder(R.drawable.ic_person_black_24dp)
                            .into(profile_image)
                }
            }
        }
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.frame_profile, fragment)
        fragmentTransaction.commit()
    }
}
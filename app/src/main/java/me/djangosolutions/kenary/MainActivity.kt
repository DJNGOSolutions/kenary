package me.djangosolutions.kenary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import me.djangosolutions.kenary.Fragments.PFragHome.PFragHome

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home ->{
                replaceFragment(PFragHome.newInstance("contents"))
                return@OnNavigationItemSelectedListener true}
            R.id.navigation_calendar ->{
                return@OnNavigationItemSelectedListener true}
            R.id.navigation_messages ->{
                return@OnNavigationItemSelectedListener true}
            R.id.navigation_profile ->{
                return@OnNavigationItemSelectedListener true}
        }
        false
    }

    private fun replaceFragment(fragment: Fragment?){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_main, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

}


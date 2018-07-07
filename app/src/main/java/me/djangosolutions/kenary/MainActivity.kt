package me.djangosolutions.kenary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.MenuItem
import android.widget.TextView
import me.djangosolutions.kenary.Adapters.ViewPagerAdapter
import me.djangosolutions.kenary.Fragments.FragClass
import me.djangosolutions.kenary.Fragments.FragTuto

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home ->
                //mTextMessage.setText(R.string.title_home);
                return@OnNavigationItemSelectedListener true
            R.id.navigation_calendar ->
                // mTextMessage.setText(R.string.title_calendar);
                return@OnNavigationItemSelectedListener true
            R.id.navigation_messages ->
                //mTextMessage.setText(R.string.title_messages);
                return@OnNavigationItemSelectedListener true
            R.id.navigation_profile ->
                //mTextMessage.setText(R.string.title_profile);
                return@OnNavigationItemSelectedListener true
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

}


package com.pw.helloworld

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.pw.helloworld.destination1.Destination1Fragment
import com.pw.helloworld.destination2.Destination2Fragment
import com.pw.helloworld.destination3.Destination3Fragment
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_destination_1 -> {
                Timber.d("Showing Destination 1")
                supportFragmentManager.beginTransaction().replace(R.id.destination_container, Destination1Fragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_destination_2 -> {
                Timber.d("Showing Destination 2")
                supportFragmentManager.beginTransaction().replace(R.id.destination_container, Destination2Fragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_destination_3 -> {
                Timber.d("Showing Destination 3")
                supportFragmentManager.beginTransaction().replace(R.id.destination_container, Destination3Fragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val selectedItemID = bottom_navigation.selectedItemId
        val selectedItem = bottom_navigation.menu.findItem(selectedItemID)
        onNavigationItemSelectedListener.onNavigationItemSelected(selectedItem)
    }
}

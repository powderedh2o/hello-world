package com.pw.helloworld

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_destination_1 -> {
                supportFragmentManager.beginTransaction().replace(R.id.destination_container, Destination1Fragment.newInstance()).commit();
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_destination_2 -> {
                supportFragmentManager.beginTransaction().replace(R.id.destination_container, Destination2Fragment.newInstance()).commit();
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_destination_3 -> {
                supportFragmentManager.beginTransaction().replace(R.id.destination_container, Destination3Fragment.newInstance()).commit();
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val selectedItemID = bottom_navigation.getSelectedItemId()
        val selectedItem = bottom_navigation.menu.findItem(selectedItemID)
        mOnNavigationItemSelectedListener.onNavigationItemSelected(selectedItem)
    }
}

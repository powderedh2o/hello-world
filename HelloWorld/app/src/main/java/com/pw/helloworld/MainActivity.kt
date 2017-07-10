package com.pw.helloworld

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_destination_1 -> {
                destination_title.setText(R.string.navigation_destination_1_title)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_destination_2 -> {
                destination_title.setText(R.string.navigation_destination_2_title)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_destination_3 -> {
                destination_title.setText(R.string.navigation_destination_3_title)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}

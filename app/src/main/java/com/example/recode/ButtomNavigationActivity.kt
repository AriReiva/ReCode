package com.example.recode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ButtomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttom_navigation)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Mengatur navigasi ke fragment sesuai dengan menu yang diklik
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home-> {
                    // Navigasi ke HomeFragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentHome())
                        .commit()
                    true
                }
                R.id.jadwal-> {
                    // Navigasi ke HomeFragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentJadwal())
                        .commit()
                    true
                }
                R.id.scan -> {
                    // Navigasi ke ProfileFragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentScan())
                        .commit()
                    true
                }
                R.id.history -> {
                    // Navigasi ke ProfileFragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentHistory())
                        .commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FragmentProfile())
                        .commit()
                    true
                }
                else -> false
            }
        }

        // Memastikan HomeFragment ditampilkan saat pertama kali aplikasi dibuka
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.home
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentHome())
                .commit()
        }
    }
}
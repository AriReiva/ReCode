package com.example.recode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cek database
        val dbHelper = DBHelper(this)
        val hasUserData = dbHelper.hasUserData()

        // Timer untuk splash screen
        Handler().postDelayed({
            if (hasUserData) {
                // Jika ada data, arahkan ke LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                // Jika tidak ada data, arahkan ke SignUpActivity
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 2000) // 2 detik splash screen

    }
}
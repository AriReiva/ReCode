package com.example.recode

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHelper = DBHelper(this)

        val etNisn = findViewById<EditText>(R.id.Login_NISN)
        val etNama = findViewById<EditText>(R.id.Login_Nama)
        val etPassword = findViewById<EditText>(R.id.Login_Pass)
        val btnLogin = findViewById<Button>(R.id.buttonLogin)

        btnLogin.setOnClickListener {
            val nisn = etNisn.text.toString().trim()
            val nama = etNama.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (nisn.isNotEmpty() && nama.isNotEmpty() && password.isNotEmpty()) {
                val user = dbHelper.loginSiswa(nisn, nama, password)
                if (user != null) {
                    Toast.makeText(this, "Login berhasil! Selamat datang, ${user.nama}", Toast.LENGTH_SHORT).show()
                    // Arahkan ke halaman berikutnya
                    startActivity(Intent(this, ButtomNavigationActivity::class.java))
                } else {
                    Toast.makeText(this, "Login gagal! Periksa kembali data Anda.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
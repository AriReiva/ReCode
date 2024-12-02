package com.example.recode

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val buttonRegisa = findViewById<Button>(R.id.buttonRegisa)

        // Initialize DBHelper
        dbHelper = DBHelper(this)

        // Set up sign-up button click listener
        buttonRegisa.setOnClickListener {
            val nisn = findViewById<EditText>(R.id.et_NISN).text.toString().trim()
            val nama = findViewById<EditText>(R.id.et_Nama).text.toString().trim()
            val kelas = findViewById<EditText>(R.id.et_Kelas).text.toString().trim()
            val jurusan = findViewById<EditText>(R.id.et_Jurusan).text.toString().trim()
            val jenkel = findViewById<EditText>(R.id.et_Jenkel).text.toString().trim()
            val password = findViewById<EditText>(R.id.et_Pass).text.toString().trim()

            // Validate inputs
            if (nisn.isEmpty() || nama.isEmpty() || kelas.isEmpty() || jurusan.isEmpty() || jenkel.isEmpty() || password.isEmpty()) {
                // Show error if any field is empty
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Create new Siswa object
                val siswa = Siswa(nama = nama, nisn = nisn, kelas = kelas, jurusan = jurusan, gender = jenkel, password = password)

                // Insert new user data into database
                val result = dbHelper.insertSiswa(siswa)

                // Check if insert was successful
                if (result != -1L) {
                    // Show success message
                    Toast.makeText(this, "Sign-Up Successful!", Toast.LENGTH_SHORT).show()

                     startActivity(Intent(this, MainActivity::class.java))
                     finish()
                } else {
                    // Show error if insertion failed
                    Toast.makeText(this, "Sign-Up Failed. Try Again!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
    package com.example.recode

    import android.os.Bundle
    import android.os.Handler
    import android.os.Looper
    import android.provider.Settings
    import android.webkit.WebView
    import android.webkit.WebViewClient
    import android.widget.Toast
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import androidx.fragment.app.FragmentTransaction

    class WebViewActivity : AppCompatActivity() {

        private val delayMillis: Long = 5000

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_web_view)

            val androidId = getAndroidId()

            // Ambil URL dari intent
            val url = intent.getStringExtra("url")

            // Ambil data dari database (misalnya NISN siswa)
            val siswaData = getSiswaDataFromDatabase() // Ambil data siswa berdasarkan kriteria

            // Jika siswa ditemukan, tambahkan data siswa ke URL
            val finalUrl = if (url != null && siswaData != null) {
                "$url?nisn=${siswaData.nisn}?android_id=?${androidId}&nama=${siswaData.nama}"
            } else {
                "$url" // URL default jika URL atau data siswa tidak ditemukan
            }

            // Setup WebView
            val webView: WebView = findViewById(R.id.webView)
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true // Enable JavaScript jika diperlukan

            // Muat URL yang sudah dimodifikasi
            webView.loadUrl(finalUrl)

        }
        // Method untuk mengambil data siswa dari database
        private fun getSiswaDataFromDatabase(): Siswa? {
            val dbHelper = DBHelper(this)
            val siswaList = dbHelper.getAllSiswa()

            // Misalnya, ambil siswa pertama dalam daftar (sesuaikan dengan logika Anda)
            return if (siswaList.isNotEmpty()) siswaList[0] else null
        }

        private fun getAndroidId(): String {
            return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        }
    }
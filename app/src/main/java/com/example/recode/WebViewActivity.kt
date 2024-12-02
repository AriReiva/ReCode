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
            
            val url = intent.getStringExtra("url")

            val siswaData = getSiswaDataFromDatabase()
            
            val finalUrl = if (url != null && siswaData != null) {
                "$url?nisn=${siswaData.nisn}?android_id=?${androidId}&nama=${siswaData.nama}"
            } else {
                "$url"
            }

            val webView: WebView = findViewById(R.id.webView)
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true 

            webView.loadUrl(finalUrl)

        }
        
        private fun getSiswaDataFromDatabase(): Siswa? {
            val dbHelper = DBHelper(this)
            val siswaList = dbHelper.getAllSiswa()

            return if (siswaList.isNotEmpty()) siswaList[0] else null
        }

        private fun getAndroidId(): String {
            return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        }
    }

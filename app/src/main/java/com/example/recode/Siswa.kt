package com.example.recode

data class Siswa(
    val id: Int = 0,        // ID siswa (opsional, SQLite bisa autoincrement)
    val nama: String,       // Nama siswa
    val nisn: String,       // NISN siswa
    val kelas: String,      // Kelas siswa
    val jurusan: String,    // Jurusan siswa
    val gender: String,     // Gender siswa
    val password: String    // Password siswa
)


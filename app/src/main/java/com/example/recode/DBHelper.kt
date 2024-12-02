package com.example.recode

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.net.IDN

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "helped.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "siswa"
        const val COLUMN_ID = "id"
        const val COLUMN_NAMA = "nama"
        const val COLUMN_NISN = "nisn"
        const val COLUMN_KELAS = "kelas"
        const val COLUMN_JURUSAN = "jurusan"
        const val COLUMN_GENDER = "gender"
        const val COLUMN_PASSWORD = "password"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
        CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAMA TEXT NOT NULL,
            $COLUMN_NISN TEXT NOT NULL UNIQUE,
            $COLUMN_KELAS TEXT NOT NULL,
            $COLUMN_JURUSAN TEXT NOT NULL,
            $COLUMN_GENDER TEXT NOT NULL,
            $COLUMN_PASSWORD TEXT NOT NULL
        )
    """
        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Menambahkan data siswa dari data class Siswa
    fun insertSiswa(siswa: Siswa): Long {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAMA, siswa.nama)
            put(COLUMN_NISN, siswa.nisn)
            put(COLUMN_KELAS, siswa.kelas)
            put(COLUMN_JURUSAN, siswa.jurusan)
            put(COLUMN_GENDER, siswa.gender)
            put(COLUMN_PASSWORD, siswa.password)
        }
        return db.insert(TABLE_NAME, null, contentValues)
    }


    // Mengambil semua data siswa dalam bentuk list of Siswa
    fun getAllSiswa(): List<Siswa> {
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        val siswaList = mutableListOf<Siswa>()

        while (cursor.moveToNext()) {
            val siswa = Siswa(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                nama = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA)),
                nisn = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NISN)),
                kelas = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KELAS)),
                jurusan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JURUSAN)),
                gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
            )
            siswaList.add(siswa)
        }
        cursor.close()
        return siswaList
    }



    // Mengecek apakah ada data pengguna
    fun hasUserData(): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME", null)
        val hasData = cursor.moveToFirst() && cursor.getInt(0) > 0
        cursor.close()
        return hasData
    }

    // Autentikasi login menggunakan data class
    fun loginSiswa(nisn: String, nama: String, password: String): Siswa? {
        val db = readableDatabase
        val query = """
        SELECT * 
        FROM $TABLE_NAME 
        WHERE $COLUMN_NISN = ? AND $COLUMN_NAMA = ? AND $COLUMN_PASSWORD = ?
    """
        val cursor = db.rawQuery(query, arrayOf(nisn, nama, password))

        var siswa: Siswa? = null
        if (cursor.moveToFirst()) {
            siswa = Siswa(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                nama = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA)),
                nisn = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NISN)),
                kelas = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KELAS)),
                jurusan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JURUSAN)),
                gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
            )
        }

        cursor.close()
        return siswa
    }
}

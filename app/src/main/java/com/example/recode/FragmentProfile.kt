package com.example.recode

import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentProfile.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentProfile : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Menginflate layout untuk fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Ambil data siswa dari database
        val siswaData = getSiswaDataFromDatabase() // Fungsi untuk mengambil data siswa

        val androidId = getAndroidId()

        // Jika siswa ditemukan, tampilkan datanya
        if (siswaData != null) {
            val tvNama: TextView = view.findViewById(R.id.tvNama)
            val tvNISN: TextView = view.findViewById(R.id.tvNISN)
            val tvKelas: TextView = view.findViewById(R.id.tvKelas2)
            val tvJur: TextView = view.findViewById(R.id.tvJur)
            val tvGender: TextView = view.findViewById(R.id.tvGender2)
            val tvAndroID: TextView = view.findViewById(R.id.tvAndroid)

            tvNISN.text = "${siswaData.nisn}"
            tvNama.text = "${siswaData.nama}"
            tvKelas.text = "${siswaData.kelas}"
            tvJur.text = "${siswaData.jurusan}"
            tvGender.text = "${siswaData.gender}"
            tvAndroID.text = "${androidId}"
        }

        return view
    }

    private fun getAndroidId(): String {
        return Settings.Secure.getString(requireContext().contentResolver, Settings.Secure.ANDROID_ID)
    }
    // Method untuk mengambil data siswa dari database
    private fun getSiswaDataFromDatabase(): Siswa? {
        val dbHelper = DBHelper(requireContext())
        val siswaList = dbHelper.getAllSiswa()

        // Misalnya, ambil siswa pertama dalam daftar (sesuaikan dengan logika Anda)
        return if (siswaList.isNotEmpty()) siswaList[0] else null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentProfile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentProfile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
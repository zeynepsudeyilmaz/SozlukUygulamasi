package com.zeynepsudeyilmaz.sozlukuygulamasi

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import com.zeynepsudeyilmaz.sozlukuygulamasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , SearchView.OnQueryTextListener {
    private lateinit var binding :ActivityMainBinding
    private lateinit var kelimelerListe:ArrayList<Kelimeler>
    private lateinit var adapter:KelimelerAdapter
    private lateinit var vt:VeritabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        veritabaniKopyala()

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbar.title = "Sözlük Uygulaması"
        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this)

        vt = VeritabaniYardimcisi(this)

        kelimelerListe = Kelimelerdao().tumKelimeler(vt)

        adapter = KelimelerAdapter(this, kelimelerListe)

        binding.rv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val item = menu?.findItem(R.id.action_ara)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        arama(query)
        Log.e("Gönderilen arama", query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        arama(newText)
        Log.e("Harf girdikçe", newText)
        return true
    }

    fun veritabaniKopyala(){
        val copyHelper = DatabaseCopyHelper(this)

        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun arama(aramaKelime:String){
        kelimelerListe = Kelimelerdao().aramaYap(vt, aramaKelime)

        adapter = KelimelerAdapter(this, kelimelerListe)

        binding.rv.adapter = adapter
    }
}
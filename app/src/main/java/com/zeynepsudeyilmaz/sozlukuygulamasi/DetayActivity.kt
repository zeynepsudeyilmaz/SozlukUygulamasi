package com.zeynepsudeyilmaz.sozlukuygulamasi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zeynepsudeyilmaz.sozlukuygulamasi.databinding.ActivityDetayBinding
import com.zeynepsudeyilmaz.sozlukuygulamasi.databinding.ActivityMainBinding

class DetayActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val kelime = intent.getSerializableExtra("nesne") as Kelimeler

        binding.textViewIng.text = kelime.ingilizce
        binding.textViewTr.text = kelime.turkce
    }
}
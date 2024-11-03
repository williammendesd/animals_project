package com.github.williammendesd.animals_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.williammendesd.animals_project.databinding.ActivityAnimalDetailsBinding

class AnimalDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recuperarDados()

        binding.btnGoBack.setOnClickListener {
            //startActivity(Intent(this, MainActivity::class.java))
            // para que a página não permaneça na pilha após retornar para o main:
            finish()
        }
    }

    private fun recuperarDados(){
        val nome = intent.getStringExtra("nome")
        binding.editNome.text = nome
        print(nome)
    }
}
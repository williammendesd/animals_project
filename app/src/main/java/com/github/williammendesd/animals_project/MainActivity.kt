package com.github.williammendesd.animals_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.williammendesd.animals_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // com o binding se acessa os IDs do XML da activity;
        binding.btnSubmitAnimal.setOnClickListener {
            submitAnimal()
        }
    }

    private fun submitAnimal(){
        // capturando dado do formulário;
        val nome = binding.inputNome.text.toString()

        // abaixo, sairemos da classe atual (this) para a AnimalDetails;
        val intent = Intent(this, AnimalDetailsActivity::class.java)

        // transferindo informações para a próxima activity
        intent.putExtra("nome", nome)

        startActivity(intent)

        // outra opção, sem guardar intent na variavel:
        //startActivity(Intent(this, AnimalDetailsActivity::class.java))
    }
}
package com.github.williammendesd.animals_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.williammendesd.animals_project.databinding.ActivityMainBinding
import com.github.williammendesd.animals_project.main.MainListAdapter
import com.github.williammendesd.animals_project.main.MainViewModel
import com.github.williammendesd.animals_project.main.MainViewModelFactory
import com.github.williammendesd.animals_project.model.Animal


// Uso do Adapter no fun setUpRecyclerView().
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val mainViewModel : MainViewModel by viewModels {
        MainViewModelFactory(( application as AnimalApplication ).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // com o binding se acessa os IDs do XML da activity;
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setUpLogo()
        setUpListeners()
        setUpRecyclerView()
    }

    private fun setUpListeners () {
        binding.btnSubmitAnimal.setOnClickListener {
            submitAnimal()
        }
        binding.btnAdd.setOnClickListener {
            insertAnimal()
        }
        binding.btnExplore.setOnClickListener {
            explore()
        }
    }

//  gravar, excluir, editar e listar os dados
    private fun setUpRecyclerView() {
        val adapter = MainListAdapter(
            onEditClick = { animal -> //showEditDialog(animal)
            },
            onDeleteClick = { animal -> mainViewModel.delete(animal) }
        )
        binding.recyclerViewAnimals.adapter = adapter
        binding.recyclerViewAnimals.layoutManager = LinearLayoutManager(this)

        // Adicionar Divider
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerViewAnimals.context,
            (binding.recyclerViewAnimals.layoutManager as
                    LinearLayoutManager).orientation
        )
        binding.recyclerViewAnimals.addItemDecoration(dividerItemDecoration)
        mainViewModel.allBoardGames.observe(this) { games ->
            games?.let { adapter.setAnimals(it) }
        }
    }

    private fun submitAnimal(){
        // capturando dado do formulário;
        val nome = binding.inputNome.text.toString()
        val image = binding.inputImage.text.toString()

        // abaixo, sairemos da classe atual (this) para a AnimalDetails;
        val intent = Intent(this, AnimalDetailsActivity::class.java)

        // transferindo informações para a próxima activity
        intent.putExtra("nome", nome)
        intent.putExtra("imagem", image)

        startActivity(intent)

        // outra opção, sem guardar intent na variavel:
        //startActivity(Intent(this, AnimalDetailsActivity::class.java))
    }

    private fun insertAnimal(){
        val name = binding.inputNome.text.toString()
        val image = binding.inputImage.text.toString()

        if (name.isNotBlank() && image.isNotBlank()) {
            mainViewModel.insert(
                Animal(name = name, image = image)
            )
            binding.inputNome.text.clear()
            binding.inputImage.text.clear()
            binding.inputNome.requestFocus()
        }

        val intent = Intent(this, AnimalDetailsActivity::class.java)

        intent.putExtra("nome", name)
        intent.putExtra("imagem", image)

        startActivity(intent)
    }

    private fun explore(){
        val intent = Intent(this, AnimalDetailsActivity::class.java)
        startActivity(intent)
    }
}
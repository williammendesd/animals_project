package com.github.williammendesd.animals_project.main

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.williammendesd.animals_project.AnimalApplication
import com.github.williammendesd.animals_project.AnimalDetailsActivity
import com.github.williammendesd.animals_project.databinding.ActivityMainBinding
import com.github.williammendesd.animals_project.databinding.DialogEditAnimalBinding
import com.github.williammendesd.animals_project.model.Animal

// A CLASSE MAIN É DEFINIDA NO ANDROID MANIFEST
// USO DO ADAPTER NO setUpRecyclerView()
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val mainViewModel : MainViewModel by viewModels {
        MainViewModelFactory(( application as AnimalApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // COM O BINDING SE ACESSA OS IDs DO XML DA ACTIVITY
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        println("aberto")
        //setUpLogo()
        setUpListeners()
        setUpRecyclerView()

        val animalObserver = Observer<Animal?> {
            binding.inputNome.text.clear()
            val intent = Intent(this, AnimalDetailsActivity::class.java)
            if(it == null){
                intent.putExtra("nome", "Animal não encontrado")
            } else {
                intent.putExtra("nome", it.name)
                intent.putExtra("image", it.image)
            }
            startActivity(intent)
        }
        mainViewModel.selectedAnimal.observe(this, animalObserver)


//        mainViewModel.selectedAnimal.observe(this){ animal ->
//            val intent = Intent(this, AnimalDetailsActivity::class.java)
//            if(animal == null){
//                intent.putExtra("titulo", "Animal não encontrado")
//            } else {
//                intent.putExtra("titulo", animal.name)
//            }
//            startActivity(intent)
//        }



    }

    private fun setUpListeners () {
        binding.btnSearchAnimal.setOnClickListener {
            searchAnimal()
        }
        binding.btnAdd.setOnClickListener {
            insertAnimal()
        }
    }

    // VINCULANDO RecyclerView XML AO ADAPTER DELE
    // INCLUINDO AÇÕES DO onClickListeners NOS COMPONENTES DO ITEM DA LISTA
    private fun setUpRecyclerView() {
        val adapter = MainListAdapter(
            onEditClick = { animal -> showEditDialog(animal)
            },
            onDeleteClick = { animal -> mainViewModel.delete(animal) }
        )

        binding.recyclerViewAnimals.adapter = adapter
        binding.recyclerViewAnimals.layoutManager = LinearLayoutManager(this)

        // ADICIONAR DIVIDER
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerViewAnimals.context,
            (binding.recyclerViewAnimals.layoutManager as
                    LinearLayoutManager).orientation
        )
        binding.recyclerViewAnimals.addItemDecoration(dividerItemDecoration)

        // LIVE DATA animais
        mainViewModel.allAnimals.observe(this) { animal ->
            animal?.let { adapter.setAnimals(it) }
        }
    }

    private fun showEditDialog(animal: Animal) {
        val dialogBinding = DialogEditAnimalBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogBinding.root)

        // PREENCHE OS CAMPOS COM OS DADOS DO ANIMAL
        dialogBinding.editAnimalName.setText(animal.name)
        dialogBinding.editAnimalImage.setText(animal.image)
        builder.setTitle("Editar Animal")
        builder.setPositiveButton("Salvar") { _, _ ->
            val updatedAnimal = animal.copy(
                name = dialogBinding.editAnimalName.text.toString(),
                image = dialogBinding.editAnimalImage.text.toString()
            )
            mainViewModel.update(updatedAnimal)
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun searchAnimal(){
        val nome = binding.inputNome.text.toString()
        mainViewModel.selectByName(nome)
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

}
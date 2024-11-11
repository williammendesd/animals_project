package com.github.williammendesd.animals_project.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.williammendesd.animals_project.databinding.AnimalItemBinding
import com.github.williammendesd.animals_project.model.Animal

// Adapter é responsável por manipular a Lista XML (RecyclerView)

class MainListAdapter(
    private val onEditClick: (Animal) -> Unit,
    private val onDeleteClick: (Animal) -> Unit
) : RecyclerView.Adapter<MainListAdapter.AnimalHolder>() {

    private var animals: List<Animal> = emptyList()

    // ViewHolder: É a referência para a view de cada item da lista;
    // o item será replicadao dentro do Adapter;
    class AnimalHolder(val binding: AnimalItemBinding) : RecyclerView.ViewHolder(binding.root)

    // a função abaixo retorna o Holder com o binding do item;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalHolder {
        val binding =
            AnimalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalHolder(binding)
    }

    // Essa função cuida do XML do RecyclerView:
    // captura os dados de um item da List<> e coloca no XML;
    // aciona os onclickListeners;
    override fun onBindViewHolder(holder: AnimalHolder, position: Int) {

        val currentAnimal = animals[position]

        Glide.with(holder.binding.root.context).load(currentAnimal.image).into(holder.binding.image)
        holder.binding.textViewAnimalName.text = currentAnimal.name

        // Aqui definimos Listeners, mas só configuramos elas na MainActivity;
        holder.binding.buttonEdit.setOnClickListener{
            onEditClick(currentAnimal)
        }
        holder.binding.buttonDelete.setOnClickListener() {
            onDeleteClick(currentAnimal)
        }
    }

    override fun getItemCount() = animals.size

    fun setAnimals(animals: List<Animal>) {
        this.animals = animals
        notifyDataSetChanged()
    }
}
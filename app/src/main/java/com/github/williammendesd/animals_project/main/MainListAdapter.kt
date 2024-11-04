package com.github.williammendesd.animals_project.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.williammendesd.animals_project.databinding.AnimalItemBinding
import com.github.williammendesd.animals_project.model.Animal

// Adapter é responsável por associar a lista de objetos a view de item e lista;
class MainListAdapter(
    private val onEditClick: (Animal) -> Unit,
    private val onDeleteClick: (Animal) -> Unit
) : RecyclerView.Adapter<MainListAdapter.AnimalHolder>() {

    private var animals: List<Animal> = emptyList()

    // ViewHolder: É a referência para a view de cada item da lista, que
    // será replicada (nesse caso dentro do Adapter);
    class AnimalHolder(val binding: AnimalItemBinding) : RecyclerView.ViewHolder(binding.root)

    // a classe abaixo retorna o Holder, imagino que retorna cada item;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalHolder {
        val binding =
            AnimalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalHolder(binding)
    }

    // Imagino que essa função funciona ao usar em um animal especifico
    override fun onBindViewHolder(holder: AnimalHolder, position: Int) {
        val currentAnimal = animals[position]

        holder.binding.textViewAnimalName.text = currentAnimal.name
        holder.binding.image.text = currentAnimal.image
    }

    override fun getItemCount() = animals.size

    fun setAnimals(animals: List<Animal>) {
        this.animals = animals
        notifyDataSetChanged()
    }
}
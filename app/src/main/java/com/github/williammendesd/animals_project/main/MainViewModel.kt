package com.github.williammendesd.animals_project.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.williammendesd.animals_project.model.Animal
import com.github.williammendesd.animals_project.repository.AnimalRepository
import kotlinx.coroutines.launch

// o 3° LiveData a ser feito (antes é feito no repository).
// MainViewModel interage o repository e é usada pela MainActivity;
// ViewModel: Gerencia o estado da tela e interage com os repositórios;

class MainViewModel(private val repository: AnimalRepository) : ViewModel() {
    val allAnimals: LiveData<List<Animal>> = repository.allAnimals
    var selectedAnimal = repository.selectedAnimal
//    var selectedAnimal : LiveData<Animal?> = _selectedAnimal

    fun insert(animal: Animal) {
        viewModelScope.launch {
            repository.insert(animal)
        }
    }

    fun update(animal: Animal) {
        viewModelScope.launch {
            repository.update(animal)
        }
    }

    fun selectByName(nome : String){
        viewModelScope.launch {
            repository.findByName(nome)
        }
    }

    fun delete(animal: Animal) {
        viewModelScope.launch {
            repository.delete(animal)
        }
    }
}

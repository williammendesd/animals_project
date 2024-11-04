package com.github.williammendesd.animals_project.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.williammendesd.animals_project.model.Animal
import com.github.williammendesd.animals_project.repository.AnimalRepository
import kotlinx.coroutines.launch

// o 3° LiveData a ser feito (antes é feito no repository).
// MainViewModel interage o repository e é usada pela MainActivity;
class MainViewModel(private val repository: AnimalRepository) : ViewModel() {
    val allBoardGames: LiveData<List<Animal>> = repository.allAnimals

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
    fun delete(animal: Animal) {
        viewModelScope.launch {
            repository.delete(animal)
        }
    }
}

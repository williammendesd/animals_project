package com.github.williammendesd.animals_project.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.williammendesd.animals_project.dao.AnimalDao
import com.github.williammendesd.animals_project.model.Animal


// o 2° LiveData a ser feito. (antes é feito na interface dao)

class AnimalRepository (private val animalDao : AnimalDao ) {
    val allAnimals : LiveData<List<Animal>> = animalDao.getAnimals()
    val selectedAnimal : MutableLiveData<Animal?> by lazy {
        MutableLiveData<Animal?>()
    }
//    var selectedAnimal : LiveData<Animal?> = _selectedAnimal

    // SUSPEND É UM TIPO DE FUNÇÃO QUE PODE SER PAUSADA SEM BLOQUEAR A THREAD ATUAL, ASSINCRONA
    suspend fun findByName(nome: String){
        selectedAnimal.value = animalDao.findByName(nome)
    }

    suspend fun insert(animal: Animal) {
        animalDao.insert(animal)
    }
    suspend fun update(animal: Animal) {
        animalDao.update(animal)
    }
    suspend fun delete(animal: Animal) {
        animalDao.delete(animal)
    }
}
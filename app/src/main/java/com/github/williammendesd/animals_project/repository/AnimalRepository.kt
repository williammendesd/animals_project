package com.github.williammendesd.animals_project.repository

import androidx.lifecycle.LiveData
import com.github.williammendesd.animals_project.dao.AnimalDao
import com.github.williammendesd.animals_project.model.Animal


// o 2° LiveData a ser feito. (antes é feito na interface dao)

class AnimalRepository (private val animalDao : AnimalDao ) {
    val allAnimals : LiveData<List<Animal>> =
        animalDao.getAnimals()

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
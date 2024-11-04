package com.github.williammendesd.animals_project

import android.app.Application
import com.github.williammendesd.animals_project.database.AnimalDatabase
import com.github.williammendesd.animals_project.repository.AnimalRepository

// responsável por inicializar o DB e repository;
class AnimalApplication : Application() {
    val database by lazy { AnimalDatabase .getDatabase( this) }
    val repository by lazy {
        AnimalRepository( database.animalDao()) }
}
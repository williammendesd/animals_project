package com.github.williammendesd.animals_project.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.williammendesd.animals_project.repository.AnimalRepository

// responsável por criar MainViewModel e injetar o repositório para fazer o uso do Banco de Dados.
// inicializa MainViewModel e vincula repositório ao banco de dados (acho).

class MainViewModelFactory (private val repository: AnimalRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom( MainViewModel ::class.java)) {
            @Suppress("UNCHECKED_CAST" )
            return MainViewModel( repository) as T
        }
        throw IllegalArgumentException( "Unknown ViewModel class" )
    }

}
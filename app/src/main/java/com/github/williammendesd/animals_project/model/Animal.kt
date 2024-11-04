package com.github.williammendesd.animals_project.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tb_animal")
data class Animal (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name : String,
    var image : String
)
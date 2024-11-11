package com.github.williammendesd.animals_project.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity("tb_animal", indices = [Index(value = ["name"], unique = true)])
data class Animal (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name : String?,
    @ColumnInfo (name = "image") val image : String?
)


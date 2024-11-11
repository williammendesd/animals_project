package com.github.williammendesd.animals_project.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.williammendesd.animals_project.model.Animal

// interface utilizada pelo repository para acessar dados do banco.
// O 1° LiveData
@Dao
interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(animal: Animal)

    @Query("SELECT * FROM tb_animal ORDER BY id ASC")
    fun getAnimals(): LiveData<List<Animal>>

    @Query("SELECT * FROM tb_animal WHERE name LIKE :nome LIMIT 1")
    suspend fun findByName(nome : String) : Animal?

    @Update
    suspend fun update(animal: Animal)

    @Delete
    suspend fun delete(animal: Animal)
}

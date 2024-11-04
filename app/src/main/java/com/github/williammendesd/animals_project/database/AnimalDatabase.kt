package com.github.williammendesd.animals_project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.williammendesd.animals_project.dao.AnimalDao
import com.github.williammendesd.animals_project.model.Animal


@Database(entities = [Animal::class], version = 1)
abstract class AnimalDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
    companion object {
        @Volatile
        private var INSTANCE: AnimalDatabase? = null
        fun getDatabase(context: Context): AnimalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimalDatabase::class.java,
                    "animal_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
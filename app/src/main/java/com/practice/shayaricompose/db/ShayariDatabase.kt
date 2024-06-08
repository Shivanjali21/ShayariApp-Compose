package com.practice.shayaricompose.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practice.shayaricompose.models.allshayari.AllShayari
import com.practice.shayaricompose.models.shayaricategory.ShayariCategory

@Database(entities = [ShayariCategory::class, AllShayari::class], version = 1, exportSchema = false)
abstract class ShayariDatabase : RoomDatabase() {
   abstract fun shayariDao() : ShayariDao

    companion object {
        private var instance: ShayariDatabase? = null
        fun getDatabaseInstance(context: Context): ShayariDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val roomDatabaseInstance = Room.databaseBuilder(
                    context.applicationContext, ShayariDatabase::class.java,
                    "Shayari Database")
                    .createFromAsset("Shayari.db").allowMainThreadQueries().build()
                instance = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}
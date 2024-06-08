package com.practice.shayariapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practice.shayariapp.model.allshayari.AllShayari
import com.practice.shayariapp.model.allshayaricategory.AllShayariCategory

@Database(entities = [AllShayariCategory::class, AllShayari::class], version = 4, exportSchema = false)
abstract class ShayariDatabase : RoomDatabase() {
   abstract fun shayariDao() : ShayariDao
   companion object {
       @Volatile
       private var instance : ShayariDatabase? = null
       fun getDatabaseInstance(context: Context) : ShayariDatabase {
          val tempInstance = instance
          if(tempInstance != null){
             return tempInstance
          }
          synchronized(this){
              val roomDatabaseInstance = Room.databaseBuilder(
                  context.applicationContext, ShayariDatabase::class.java, "shayari_database"
              ).createFromAsset("shayari.db").allowMainThreadQueries().build()
              instance = roomDatabaseInstance
              return roomDatabaseInstance
          }
       }
   }
}


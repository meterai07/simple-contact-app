package com.example.myapplication.data.contact

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Contact::class],
    version = 1,
    exportSchema = true
)
abstract class ContactDatabase: RoomDatabase() {
    abstract val dao: ContactDao

//    companion object {
//        @Volatile
//        private var INSTANCE: ContactDatabase? = null
//
//        fun getDatabase(context: Context): ContactDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ContactDatabase::class.java,
//                    "contact-database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}
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
}
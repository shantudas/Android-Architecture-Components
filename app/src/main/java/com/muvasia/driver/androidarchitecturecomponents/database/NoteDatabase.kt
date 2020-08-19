package com.muvasia.driver.androidarchitecturecomponents.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Note::class), version = 1,exportSchema = false)
public abstract class NoteDatabase : RoomDatabase() {


    abstract fun notedao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java, "note_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
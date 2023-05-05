package com.example.githubseach.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteUser::class], version = 1)
abstract class FavDatabase : RoomDatabase() {
    abstract fun favDao(): FavoriteDao
    companion object {
        @Volatile
        private var INSTANCE: FavDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): FavDatabase {
            if (INSTANCE == null) {
                synchronized(FavDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavDatabase::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE as FavDatabase
        }
    }
}
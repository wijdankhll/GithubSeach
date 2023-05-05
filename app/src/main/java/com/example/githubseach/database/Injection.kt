package com.example.githubseach.database

import android.content.Context
import com.example.githubseach.API.ApiConfig

object Injection {
    fun provideRepository(context: Context): FavRepository {
        ApiConfig.getApiService()
        val database = FavDatabase.getDatabase(context)
        val dao = database.favDao()

        return FavRepository.getInstance(dao)
    }
}
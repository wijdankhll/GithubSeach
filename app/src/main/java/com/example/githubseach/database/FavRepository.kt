package com.example.githubseach.database

import androidx.lifecycle.LiveData

class FavRepository private constructor(

    private val newsDao: FavoriteDao

) {
    fun getFavoriteUser():LiveData<List<FavoriteUser>> {
        return newsDao.getFavoriteUser()
    }

    suspend fun setFavoriteUser(news: FavoriteUser, bookmarkState: Boolean) {
        news.isFavorite = bookmarkState
        newsDao?.insert(news)

    }
    suspend fun deleteFav(news: FavoriteUser, bookmarkState: Boolean) {
        news.isFavorite = bookmarkState
        newsDao?.delete(news)

    }



    companion object {
        @Volatile
        private var instance: FavRepository? = null
        fun getInstance(
            newsDao: FavoriteDao,
        ): FavRepository =
            instance ?: synchronized(this) {
                instance ?: FavRepository(newsDao)
            }.also { instance = it }
    }
}
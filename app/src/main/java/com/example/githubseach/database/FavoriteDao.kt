package com.example.githubseach.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: FavoriteUser)

    @Update
    fun update(note: FavoriteUser)

    @Delete
    suspend fun delete(note: FavoriteUser)

    @Query("SELECT * FROM FavoriteUser WHERE isFavorite=1")
    fun getFavoriteUser(): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM FavoriteUser WHERE login = :username")
    fun getFavoriteUserbyUserName(username: String): LiveData<List<FavoriteUser>>
}
package com.example.githubseach.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubseach.database.FavRepository
import com.example.githubseach.database.FavoriteUser
import kotlinx.coroutines.launch

class FavoriteViewModel(private val newsRepository: FavRepository) : ViewModel() {


    fun getFavorited() = newsRepository.getFavoriteUser()

    fun insertordeletefav(news: FavoriteUser, isfav : Boolean){
        viewModelScope.launch {
            if (isfav){
                newsRepository.deleteFav(news, false)
            }else{
                newsRepository.setFavoriteUser(news, true)
            }
        }
    }
}
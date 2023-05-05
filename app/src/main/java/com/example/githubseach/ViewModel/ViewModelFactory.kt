package com.example.githubseach.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubseach.UI.Activity.SettingPreferences
import com.example.githubseach.database.FavRepository
import com.example.githubseach.database.Injection

class ViewModelFactory(private val pref: SettingPreferences?, private val newsRepository: FavRepository?) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DarkModeViewModel::class.java)) {
            return pref?.let { DarkModeViewModel(it) } as T
        }else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(newsRepository!!) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(null, Injection.provideRepository(context))
            }.also { instance = it }
    }
}
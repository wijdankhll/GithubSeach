package com.example.githubseach.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubseach.API.ApiConfig
import com.example.githubseach.Response.DetailUserResponse
import com.example.githubseach.database.FavDatabase
import com.example.githubseach.database.FavoriteDao
import com.example.githubseach.database.FavoriteUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel (application: Application): AndroidViewModel(application) {
    val user = MutableLiveData<DetailUserResponse>()


    private var  userDao: FavoriteDao?
    private var userDb: FavDatabase?

    init {
        userDb = FavDatabase.getDatabase(application)
        userDao = userDb?.favDao()

    }


    fun setUserDetail(username: String) {
        ApiConfig.getApiService()
            .getDetailUser(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }





    fun getUserDetail(): LiveData<DetailUserResponse> {
        return user
    }






}


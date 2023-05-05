package com.example.githubseach.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubseach.API.ApiConfig
import com.example.githubseach.ItemsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel: ViewModel() {
    val listFollowers = MutableLiveData<List<ItemsItem>>()
    val listFollowing = MutableLiveData<List<ItemsItem>>()

    fun setListFollowers(username: String){
        ApiConfig.getApiService()
            .getFollowers(username)
            .enqueue(object : Callback<List<ItemsItem>> {
                override fun onResponse(
                    call: Call<List<ItemsItem>>,
                    response: Response<List<ItemsItem>>
                ) {
                    if (response.isSuccessful){
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }
    fun getListFollowers(): LiveData<List<ItemsItem>> {
        return listFollowers
    }

    fun setListFollowing(username: String){
        ApiConfig.getApiService()
            .getFollowing(username)
            .enqueue(object : Callback<List<ItemsItem>> {
                override fun onResponse(
                    call: Call<List<ItemsItem>>,
                    response: Response<List<ItemsItem>>
                ) {
                    if (response.isSuccessful){
                        listFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }
    fun getListFollowing(): LiveData<List<ItemsItem>> {
        return listFollowing
    }
}
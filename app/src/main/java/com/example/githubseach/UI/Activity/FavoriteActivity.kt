package com.example.githubseach.UI.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubseach.Adapter.FavoriteAdapter
import com.example.githubseach.Adapter.UserAdapter
import com.example.githubseach.ItemsItem
import com.example.githubseach.R
import com.example.githubseach.ViewModel.FavoriteViewModel
import com.example.githubseach.ViewModel.ViewModelFactory
import com.example.githubseach.database.FavoriteUser
import com.example.githubseach.databinding.ActivityDetailBinding
import com.example.githubseach.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteAdapter
    private val viewModel: FavoriteViewModel by viewModels{ ViewModelFactory.getInstance(application)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FavoriteAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvUser.adapter = adapter
        }


        viewModel.getFavorited().observe(this) { bookmarkedNews ->
            binding?.PBar?.visibility = View.GONE
            adapter.setList(bookmarkedNews)

        }







    }

}

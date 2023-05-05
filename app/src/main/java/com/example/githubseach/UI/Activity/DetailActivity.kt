package com.example.githubseach.UI.Activity

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.githubseach.API.ApiConfig
import com.example.githubseach.Adapter.FavoriteAdapter

import com.example.githubseach.R
import com.example.githubseach.Response.DetailUserResponse
import com.example.githubseach.ViewModel.DetailViewModel
import com.example.githubseach.ViewModel.FavoriteViewModel
import com.example.githubseach.ViewModel.MainViewModel
import com.example.githubseach.ViewModel.ViewModelFactory
import com.example.githubseach.database.FavRepository
import com.example.githubseach.database.FavoriteUser
import com.example.githubseach.databinding.ActivityDetailBinding
import com.example.githubusersearch.main.Adapter.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.activity.viewModels

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR = "extra_avatar"

    }
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private val _viewModel: FavoriteViewModel by viewModels{ ViewModelFactory.getInstance(application)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showLoading(true)
        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_AVATAR)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        val SPAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            VPager.adapter = SPAdapter
            tabs.setupWithViewPager(VPager)
        }



        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        username?.let { viewModel.setUserDetail(it) }



        viewModel.getUserDetail().observe(this, {
            if (it != null){
                binding.apply {
                    tvName.text = it.name
                    TvUsername.text = it.login
                    tvFllwrs.text = "${it.followers} Followers"
                    tvFllwng.text = "${it.following} Following"
                    Glide.with(this@DetailActivity)
                        .load(it.avatarUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)
                }
                showLoading(false)
            }
        })





        username?.let { viewModel.setUserDetail(it) }


        _viewModel.getFavorited()?.observe(this) { list ->

            val isFav = list.any {
                it.username == username
            }



            binding.toggleFav.setOnClickListener{
                val user = username?.let { FavoriteUser(it, avatarUrl, false) }
                if (user != null) _viewModel.insertordeletefav(user, list.any {
                    it.username==username
                })

            }
            setToggle(isFav)
        }



    }
    private fun setToggle(isFav: Boolean){
        binding.toggleFav.apply {
            if (isFav){
                setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        this@DetailActivity, R.drawable.baseline_favorite_red_24
                    )
                )
            }else{
                setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        this@DetailActivity, R.drawable.baseline_favorite_border_24
                    )
                )
            }
        }
    }



    private fun showLoading(state: Boolean) {
        if (state) {
            binding.PBar.visibility = View.VISIBLE
        }else {
            binding.PBar.visibility = View.GONE
        }
    }
}
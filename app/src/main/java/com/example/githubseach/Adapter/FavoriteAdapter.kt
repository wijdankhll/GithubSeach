package com.example.githubseach.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.githubseach.ItemsItem
import com.example.githubseach.R
import com.example.githubseach.UI.Activity.DetailActivity
import com.example.githubseach.ViewModel.FavDifutil
import com.example.githubseach.database.FavoriteUser
import com.example.githubseach.databinding.ActivityDetailBinding
import com.example.githubseach.databinding.ItemUserBinding

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    private var list = emptyList<FavoriteUser>()


    fun setList(user: List<FavoriteUser>){
        val diffutil = DiffUtil.calculateDiff(FavDifutil(list, user))
        this.list = user
        diffutil.dispatchUpdatesTo(this)

    }

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(news: FavoriteUser) {
            binding.tvUsername.text = news.username
            Glide.with(itemView.context)
                .load(news.avatarUrl)
                .into(binding.idUser)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USERNAME, news.username)
                itemView.context.startActivity(intent)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


}
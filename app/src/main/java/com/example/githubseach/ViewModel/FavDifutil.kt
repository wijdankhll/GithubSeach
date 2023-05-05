package com.example.githubseach.ViewModel

import androidx.recyclerview.widget.DiffUtil
import com.example.githubseach.database.FavoriteUser

class FavDifutil(
    private val currentlist: List<FavoriteUser>,
    private val updateList: List<FavoriteUser>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = currentlist.size

    override fun getNewListSize(): Int = updateList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =currentlist == updateList

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val currnt = currentlist[oldItemPosition].username
        val updated = updateList[oldItemPosition].username
        return currnt == updated
    }
}
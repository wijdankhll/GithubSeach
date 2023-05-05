package com.example.githubseach.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "FavoriteUser")
@Parcelize
data class FavoriteUser(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "login")
    var username: String = "",
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = null,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = null
): Parcelable
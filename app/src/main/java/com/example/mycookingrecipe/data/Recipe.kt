package com.example.mycookingrecipe.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String,
    var ingredients: String,
    var description: String,
    var image: String
) : Parcelable
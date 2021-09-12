package com.example.mycookingrecipe.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: String,
    val description: String,
    val image: String
) : Parcelable
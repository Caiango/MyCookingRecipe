package com.example.mycookingrecipe.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    var id: Int,
    var name: String,
    var ingredients: String,
    var description: String,
    var image: String
) : Parcelable
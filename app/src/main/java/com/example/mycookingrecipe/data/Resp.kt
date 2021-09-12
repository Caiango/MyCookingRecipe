package com.example.mycookingrecipe.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Resp(val recipes: List<Recipe>) : Parcelable
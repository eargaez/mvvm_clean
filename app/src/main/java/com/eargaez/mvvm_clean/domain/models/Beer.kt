package com.eargaez.mvvm_clean.domain.models

import com.google.gson.annotations.SerializedName

data class Beer(
    val id: Int,
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val tagline: String,
    val description: String
)

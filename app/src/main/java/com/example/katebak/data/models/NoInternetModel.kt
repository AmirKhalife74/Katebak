package com.example.katebak.data.models

data class NoInternetModel(
    val status: Boolean?,
    val message: String="دسترسی به اینترنت موجود نیست",
    val function: ()->Unit
)
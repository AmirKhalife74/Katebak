package com.example.katebak.data.models

import java.io.Serializable

data class OtpResponseModel(
    val message: String,
    val data: Data,
):Serializable

data class Data(
    val token: String,
    val isActive: Boolean,
):Serializable
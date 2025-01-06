package com.example.katebak.data.models

import com.squareup.moshi.Json
import java.io.Serializable

data class LoginRequest(
    @Json(name = "phoneNumber")var phoneNumber:String
):Serializable

package com.example.katebak.data.models

import com.squareup.moshi.Json
import java.io.Serializable

data class CheckOtpModel(
    @Json(name = "phoneNumber") var phoneNumber:String,
    @Json(name = "otpCode") var otpCode:String,
    @Json(name = "problem") var problem:String
):Serializable

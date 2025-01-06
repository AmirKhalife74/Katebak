package com.example.katebak.data.models

import com.squareup.moshi.Json

data class ProfileModel(
    @Json(name = "firstName") var firstName: String,
    @Json(name = "lastName") var lastName: String
)

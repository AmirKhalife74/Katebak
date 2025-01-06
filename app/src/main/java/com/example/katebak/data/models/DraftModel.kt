package com.example.katebak.data.models

import com.squareup.moshi.Json

data class DraftModel(
    @Json(name = "problem") var problem:String
)

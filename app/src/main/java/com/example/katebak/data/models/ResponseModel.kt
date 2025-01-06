package com.example.katebak.data.models

import com.squareup.moshi.Json

class ResponseModel (
    @Json(name = "message")var message: String,
    @Json(name = "data") var data: Any?
)
package com.example.katebak.data.models

import com.squareup.moshi.Json
import java.io.Serializable

data class UserProfileModelResponse(
    val message: String,
    val data: UserData,
)

data class UserData(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val subjects: List<Subject>,
)

data class Subject(
    val id: String,
    val subject: String,
)

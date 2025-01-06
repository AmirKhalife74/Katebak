package com.example.katebak.data.models

import com.squareup.moshi.Json
import java.io.Serializable

data class DraftResponseModel(
   @Json(name = "message")val message: String,
   @Json(name = "data")val data: DraftData?,
):Serializable

data class DraftData(
   @Json(name = "draftId")val draftId: String,
   @Json(name = "subject")val subject: String,
   @Json(name = "content")val content: List<Content>,
):Serializable

data class Content(
   @Json(name = "isHeading") val isHeading: Boolean,
   @Json(name = "content") val content: String,
):Serializable


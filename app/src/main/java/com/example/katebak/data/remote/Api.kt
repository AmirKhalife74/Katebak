package com.example.katebak.data.remote

import com.example.katebak.data.models.CheckOtpModel
import com.example.katebak.data.models.DraftModel
import com.example.katebak.data.models.DraftResponseModel
import com.example.katebak.data.models.LoginRequest
import com.example.katebak.data.models.OtpResponseModel
import com.example.katebak.data.models.ProfileModel
import com.example.katebak.data.models.ResponseModel
import com.example.katebak.data.models.UserProfileModelResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("")
    suspend fun test():Response<String>

    @POST("logIn")
    suspend fun login(@Body phoneNumber: LoginRequest):Response<ResponseModel>

    @POST("checkOtp")
    suspend fun checkOtp(@Body otpModel: CheckOtpModel):Response<OtpResponseModel>

    @POST("newDraft")
    suspend fun newDraft(@Body problem:DraftModel):Response<DraftResponseModel>

    @POST("updateProfile")
    suspend fun updateProfile(@Body profileModel: ProfileModel):Response<ResponseModel>

    @GET("fetchUserData")
    suspend fun getUserProfile():Response<UserProfileModelResponse>

}
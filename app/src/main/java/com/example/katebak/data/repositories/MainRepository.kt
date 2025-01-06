package com.example.katebak.data.repositories

import android.content.Context
import com.example.katebak.data.models.CheckOtpModel
import com.example.katebak.data.models.DraftModel
import com.example.katebak.data.models.DraftResponseModel
import com.example.katebak.data.models.LoginRequest
import com.example.katebak.data.models.OtpResponseModel
import com.example.katebak.data.models.ProfileModel
import com.example.katebak.data.models.ResponseModel
import com.example.katebak.data.models.UserProfileModelResponse
import com.example.katebak.data.remote.Api
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject


class MainRepository @Inject constructor(var api: Api, @ApplicationContext var context: Context) {

    suspend fun test():Response<String>
    {
        return api.test()
    }


    suspend fun login(phoneNumber: LoginRequest): Response<ResponseModel> {
        return api.login(phoneNumber)
    }

    suspend fun checkOtp(otpModel: CheckOtpModel): Response<OtpResponseModel> {
        return api.checkOtp(otpModel)
    }

    suspend fun newDraft(problem: DraftModel): Response<DraftResponseModel> {
        return api.newDraft(problem)
    }

    suspend fun updateProfile(profileModel: ProfileModel): Response<ResponseModel> {
        return api.updateProfile(profileModel)
    }

    suspend fun getUserProfile():Response<UserProfileModelResponse>
    {
        return api.getUserProfile()
    }

}
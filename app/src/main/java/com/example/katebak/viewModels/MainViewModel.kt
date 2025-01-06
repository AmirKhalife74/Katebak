package com.example.katebak.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.katebak.data.models.CheckOtpModel
import com.example.katebak.data.models.DraftModel
import com.example.katebak.data.models.DraftResponseModel
import com.example.katebak.data.models.LoginRequest
import com.example.katebak.data.models.OtpResponseModel
import com.example.katebak.data.models.ProfileModel
import com.example.katebak.data.models.ResponseModel
import com.example.katebak.data.models.UserProfileModelResponse
import com.example.katebak.data.repositories.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private var repository: MainRepository) : ViewModel() {

    private var _loginResponse = MutableLiveData<ResponseModel?>(null)
    val loginResponse: MutableLiveData<ResponseModel?> get() = _loginResponse
    fun _clearLoginResponse() = _loginResponse.postValue(null)

    suspend fun login(phoneNumber: LoginRequest) {
        phoneNumber.let {
            repository.login(phoneNumber).let { data ->
                if (data.isSuccessful) {
                    _loginResponse.postValue(data.body())
                } else {
                    _loginResponse.postValue(null)
                }

            }
        }
    }


    private var _checkOtpResponse = MutableLiveData<OtpResponseModel?>(null)
    val checkOtpResponse: MutableLiveData<OtpResponseModel?> get() = _checkOtpResponse
    fun _clearCheckOtpResponse() = _checkOtpResponse.postValue(null)

    suspend fun checkOtp(otpModel: CheckOtpModel) {
        otpModel.let {
            repository.checkOtp(otpModel).let { data ->

                if (data.isSuccessful) {
                    _checkOtpResponse.postValue(data.body())
                } else {
                    _checkOtpResponse.postValue(null)
                }

            }
        }

    }


    private var _newDraftResponse = MutableLiveData<DraftResponseModel?>(null)
    val newDraftResponse: MutableLiveData<DraftResponseModel?> get() = _newDraftResponse
    fun _clearNewDraftResponse() = _newDraftResponse.postValue(null)

    suspend fun newDraft(draft: DraftModel) {
        if (draft.problem.isNotEmpty() || draft.problem.isNotBlank()) {
            repository.newDraft(draft).let { data ->
                if (data.isSuccessful) {
                    _clearNewDraftResponse()
                    _newDraftResponse.postValue(data.body())
                } else {
                    _newDraftResponse.postValue(null)
                }
            }
        }
    }


    private var _updateProfileResponse = MutableLiveData<ResponseModel?>(null)
    val updateProfileResponse: MutableLiveData<ResponseModel?> get() = _updateProfileResponse
    fun _clearUpdateProfileResponse() = _updateProfileResponse.postValue(null)

    suspend fun updateProfile(profileModel: ProfileModel) {
        profileModel.let {
            repository.updateProfile(profileModel).let { data ->
                if (data.isSuccessful) {
                    _updateProfileResponse.postValue(data.body())
                } else {
                    _updateProfileResponse.postValue(null)
                }
            }
        }

    }

    private var _getUserProfileResponse = MutableLiveData<UserProfileModelResponse?>(null)
    val getUserProfileResponse: MutableLiveData<UserProfileModelResponse?> get() = _getUserProfileResponse
    fun _clearGetUserProfileResponse() = _updateProfileResponse.postValue(null)

    suspend fun getUserProfile() {

        repository.getUserProfile().let { data ->
            if (data.isSuccessful) {
                _getUserProfileResponse.postValue(data.body())
            } else {
                _getUserProfileResponse.postValue(null)
            }
        }


    }

}
package com.example.katebak.views.auth

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.katebak.R
import com.example.katebak.data.models.CheckOtpModel
import com.example.katebak.data.models.DraftModel
import com.example.katebak.data.models.LoginRequest
import com.example.katebak.databinding.FragmentAuthBinding
import com.example.katebak.databinding.FragmentOtpBinding
import com.example.katebak.utils.AuthNavigationListener
import com.example.katebak.utils.Env
import com.example.katebak.utils.Env.userPhoneNumber
import com.example.katebak.utils.Env.userProblem
import com.example.katebak.utils.convertPersianToLatinNumbers
import com.example.katebak.viewModels.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OtpFragment(private val listener: AuthNavigationListener) : Fragment() {

    private lateinit var binding: FragmentOtpBinding
    private lateinit var countDownTimer: CountDownTimer
    lateinit var phoneNumber: String

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listen()
        observe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun init() {
        setCountDown(90000)
    }

    private fun listen() {
        binding.apply {
            btnSubmitLogin.setOnClickListener {
                mainViewModel.viewModelScope.launch {
                    val otp = CheckOtpModel(
                        phoneNumber = convertPersianToLatinNumbers(userPhoneNumber),
                        otpCode = convertPersianToLatinNumbers(edtUsername.text.toString()),
                        problem = userProblem
                    )
                    mainViewModel.checkOtp(otp)
                }

            }

            tvCountDown.setOnClickListener {
                if (tvCountDown.text.equals("دریافت مججد کد تایید")) {
                    mainViewModel.viewModelScope.launch {
                        val loginRequest = LoginRequest(phoneNumber = userPhoneNumber)
                        mainViewModel.login(loginRequest)
                    }
                }
            }
        }
    }

    private fun observe() {
        mainViewModel.viewModelScope.launch {
            mainViewModel.loginResponse.observe(viewLifecycleOwner) { result ->
                result?.let {
                    setCountDown(90000)

                }


            }

            mainViewModel.checkOtpResponse.observe(viewLifecycleOwner) { result ->
                result?.let {
                    Env.store.setBoolean("isLogin", true)
                    Env.store.setString("access_token", it.data.token)
                    val draft = DraftModel(Env.userProblem)
                    if (draft.problem.isNotBlank()) {
                        mainViewModel.viewModelScope.launch {
                            mainViewModel.newDraft(draft)
                        }
                    } else {
                        val action = OtpFragmentDirections.actionOtpFragmentToDraftResultFragment()
                        findNavController().navigate(action)
                    }

                }
            }
            mainViewModel.newDraftResponse.observe(viewLifecycleOwner) { data ->
                data?.data.let {
                    if (it != null) {
                        val json = Gson().toJson(it)
                        val action = OtpFragmentDirections.actionOtpFragmentToDraftResultFragment()
                        findNavController().navigate(action)
                    }


                }
            }
        }
    }

    private fun setCountDown(countdownTime: Long) {
        binding.apply {
            countDownTimer = object : CountDownTimer(countdownTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    // Update the TextView with remaining time
                    tvCountDown.text = "زمان باقی ماندع: ${millisUntilFinished / 1000} ثانیه"
                }

                override fun onFinish() {
                    // Handle the countdown completion
                    tvCountDown.text = "دریافت مججد کد تایید"
                }
            }
        }
    }


}
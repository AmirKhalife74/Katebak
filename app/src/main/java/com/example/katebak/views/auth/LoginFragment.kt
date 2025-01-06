package com.example.katebak.views.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import com.example.katebak.R
import com.example.katebak.data.models.LoginRequest
import com.example.katebak.databinding.FragmentAuthBinding
import com.example.katebak.databinding.FragmentLoginBinding
import com.example.katebak.utils.AuthNavigationListener
import com.example.katebak.utils.Env.userPhoneNumber
import com.example.katebak.utils.convertPersianToLatinNumbers
import com.example.katebak.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment(private val listener: AuthNavigationListener) : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun init() {
        binding.apply {
            edtUsername.setText("")
        }
    }

    private fun listen() {
        binding.apply {
            btnSubmitLogin.setOnClickListener {
                mainViewModel.viewModelScope.launch {
                    val loginRequest =
                        LoginRequest(convertPersianToLatinNumbers(edtUsername.text.toString()))
                    mainViewModel.login(loginRequest)
                }
            }
        }
    }

    private fun observe() {
        mainViewModel.viewModelScope.launch {
            mainViewModel.loginResponse.observe(viewLifecycleOwner) { resualt ->
                resualt?.let {
                    userPhoneNumber = binding.edtUsername.text.toString()
                    listener.navigateTo(1)
                }

            }
        }
    }

}
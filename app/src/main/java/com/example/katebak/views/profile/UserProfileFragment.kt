package com.example.katebak.views.profile

import android.os.Bundle
import android.system.Os.listen
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import com.example.katebak.R
import com.example.katebak.databinding.FragmentProfileBinding
import com.example.katebak.databinding.FragmentUserProfileBinding
import com.example.katebak.utils.Env
import com.example.katebak.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var binding: FragmentUserProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUserProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listen()
        observe()
    }

    private fun observe() {
        mainViewModel.getUserProfileResponse.observe(viewLifecycleOwner) { response ->
            response?.let {
                response.data.let {
                    binding.apply {
                        if (it.firstName.isEmpty()) {
                            tvName.text = it.firstName
                        } else {
                            tvName.text = "هنوز نامی وارد نکرده اید !"
                        }

                        if (it.lastName.isEmpty()) {
                            tvLastName.text = it.lastName
                        } else {
                            tvLastName.text = "هنوز نام خانوادگی را وارد نکرده اید !"
                        }

                        if (it.phoneNumber.isEmpty()) {
                            tvPhoneNumber.text = it.phoneNumber
                        }
                    }
                }
            }
        }
    }

    private fun listen() {

    }

    private fun init() {
        mainViewModel.viewModelScope.launch {
            mainViewModel.getUserProfile()
        }

    }
}
package com.example.katebak.views.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import com.example.katebak.databinding.FragmentProfileBinding
import com.example.katebak.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var binding:FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listen()
        observe()
    }

    private fun init(){
        mainViewModel.viewModelScope.launch {
            mainViewModel.getUserProfile()
        }
    }
    private fun listen(){}
    private fun observe(){
        mainViewModel.getUserProfileResponse.observe(viewLifecycleOwner){ resualt ->

            resualt?.let {
                resualt.data
            }

        }
    }
}
package com.example.katebak.views.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.katebak.R
import com.example.katebak.databinding.FragmentAuthBinding
import com.example.katebak.utils.AuthNavigationListener
import com.example.katebak.viewModels.MainViewModel
import com.example.katebak.views.auth.adapter.AuthPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : Fragment(), AuthNavigationListener {

    private lateinit var binding:FragmentAuthBinding

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
        binding = FragmentAuthBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun init(){
        binding.apply {
            authViewpager.adapter = AuthPagerAdapter(this@AuthFragment)

            // Handle communication between fragments
            setFragmentListeners()
        }
    }
    private fun listen(){}
    private fun observe(){}

    private fun setFragmentListeners() {
        binding.apply {
            parentFragmentManager.setFragmentResultListener("navigateToRegister", this@AuthFragment) { _, _ ->
                authViewpager.setCurrentItem(0, true)
            }
            parentFragmentManager.setFragmentResultListener("navigateToLogin", this@AuthFragment) { _, _ ->
                authViewpager.setCurrentItem(1, true)
            }
        }

    }

    override fun navigateTo(position: Int) {
        binding.apply {
            authViewpager.setCurrentItem(position, true)
        }

    }
}
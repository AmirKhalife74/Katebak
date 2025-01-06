package com.example.katebak.views.main

import android.os.Bundle
import android.system.Os.listen
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.katebak.R
import com.example.katebak.databinding.FragmentAuthBinding
import com.example.katebak.databinding.FragmentMainBinding
import com.example.katebak.viewModels.MainViewModel
import javax.inject.Inject


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

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
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }


    private fun observe() {

    }

    private fun listen() {

    }

    private fun init() {

    }


}
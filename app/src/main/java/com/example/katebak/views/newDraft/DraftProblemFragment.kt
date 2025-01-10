package com.example.katebak.views.newDraft

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.katebak.R
import com.example.katebak.data.models.DraftModel
import com.example.katebak.databinding.FragmentNewProblemBinding
import com.example.katebak.utils.Env
import com.example.katebak.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DraftProblemFragment : Fragment() {

    private lateinit var binding: FragmentNewProblemBinding

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
        binding = FragmentNewProblemBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun init() {
        mainViewModel.viewModelScope.launch {
            mainViewModel._clearNewDraftResponse()
        }
    }

    private fun listen() {
        binding.apply {
            btnSubmit.setOnClickListener {
                if (Env.store.getBoolean("isLogin")) {
                    val draft = DraftModel(edtProblem.text.toString())
                    if(edtProblem.text.toString().isNotBlank() && edtProblem.text.toString().isNotEmpty() && edtProblem.text.toString().length < 10)
                    {
                        mainViewModel.viewModelScope.launch {
                            mainViewModel.newDraft(draft)
                            btnSubmit.text = ""
                            binding.buttonProgressBar.visibility = View.VISIBLE
                        }
                    }
                    else
                    {
                        Toast.makeText(requireContext(),"لطفا توضیحات خود را کامل بنویسید !",Toast.LENGTH_LONG).show()
                    }


                } else {
                    findNavController().navigate(R.id.action_draftProblemFragment_to_authFragment)
                }

                // userProblem = edtProblem.text.toString()
            }
        }
    }

    private fun observe() {
        mainViewModel.viewModelScope.launch {
            mainViewModel.newDraftResponse.observe(viewLifecycleOwner){ data ->
                data?.data?.let {
                    val action = DraftProblemFragmentDirections.actionDraftProblemFragmentToDraftResultFragment()
                    findNavController().navigate(action)
                    binding.buttonProgressBar.visibility = View.GONE
                    binding.btnSubmit.text = "دریافت لایحه"
                }
            }
        }
    }
}
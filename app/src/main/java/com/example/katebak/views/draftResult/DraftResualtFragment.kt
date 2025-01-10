package com.example.katebak.views.draftResult

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.katebak.data.models.DraftResponseModel
import com.example.katebak.databinding.FragmentDraftResualtBinding
import com.example.katebak.utils.copyToClipboard
import com.example.katebak.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DraftResultFragment : Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    var content: DraftResponseModel? = null
    private lateinit var binding: FragmentDraftResualtBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDraftResualtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listen()
        observe()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        mainViewModel.viewModelScope.launch {
            content = mainViewModel.newDraftResponse.value
        }
        binding.apply {
            tvDraftSubject.text = content?.data?.subject ?: ""
            content?.data?.content?.forEach {
                tvDraftContent.text = tvDraftContent.text.toString() + it.content
            }
        }

    }

    private fun listen() {
        binding.apply {
            btnCopy.setOnClickListener {
                val copyString = tvDraftSubject.text.toString() + "\n" + tvDraftContent.text .toString()
                copyToClipboard(requireContext(), copyString)
            }
        }
    }

    private fun observe() {}
}
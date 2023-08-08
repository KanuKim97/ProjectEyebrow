package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentWriteContentBinding
import com.example.projecteyebrow.viewModel.WriteContentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class WriteContentFragment : Fragment(), View.OnClickListener {
    @Inject lateinit var toastMessage: Toast

    private lateinit var title: String
    private lateinit var content: String

    private var _binding: FragmentWriteContentBinding? = null
    private val binding: FragmentWriteContentBinding get() = _binding!!
    private val writeCommunityViewModel: WriteContentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWriteContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        writeCommunityViewModel.isSaveSuccess.observe(viewLifecycleOwner) {
            if (it.isSuccess) {
                toastMessage.apply { setText(getString(R.string.SaveSuccess_MSG)) }.show()
                toCommunityFragment()
            }
        }

        binding.UploadBtn.setOnClickListener(this)
        binding.TemporarySaveBtn.setOnClickListener(this)
        binding.TemporaryLoadBtn.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setTitle(): String = binding.TitleInput.text.toString()

    private fun setContent(): String = binding.ContentInput.text.toString()

    private fun toCommunityFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, CommunityFragment())
        .commit()

    private fun toTemporaryContentFragment(): Int = requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(R.id.FragmentContainer, TemporaryContentFragment())
        .commit()

    private fun saveTemporaryContent(
        title: String,
        content: String
    ): Job = writeCommunityViewModel.temporarySaveContent(title, content)

    private fun uploadCommunityContent(
        title: String,
        content: String
    ): Job = writeCommunityViewModel.uploadCommunityContent(title, content)

    override fun onClick(view: View?) {
        title = setTitle()
        content = setContent()

        when (view?.id) {
            R.id.TemporarySave_Btn -> { saveTemporaryContent(title, content) }
            R.id.TemporaryLoad_Btn -> { toTemporaryContentFragment() }
            R.id.Upload_Btn -> { uploadCommunityContent(title, content) }
        }
    }

}
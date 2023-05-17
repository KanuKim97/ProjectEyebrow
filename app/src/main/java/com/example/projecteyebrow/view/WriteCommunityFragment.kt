package com.example.projecteyebrow.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.projecteyebrow.R
import com.example.projecteyebrow.databinding.FragmentWriteCommunityBinding
import com.example.projecteyebrow.viewModel.WriteCommunityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class WriteCommunityFragment : Fragment(), View.OnClickListener {
    @Inject lateinit var toastMessage: Toast

    private var _binding: FragmentWriteCommunityBinding? = null
    private val binding: FragmentWriteCommunityBinding get() = _binding!!
    private val writeCommunityViewModel: WriteCommunityViewModel by viewModels()

    private val title: String by lazy { setTitle() }
    private val content: String by lazy { setContent() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWriteCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isTemporarySaveComplete()

        binding.TemporarySaveBtn.setOnClickListener(this)
        binding.TemporaryLoadBtn.setOnClickListener(this)
        binding.UploadBtn.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setTitle(): String = binding.TitleInput.text.toString()

    private fun setContent(): String = binding.ContentInput.text.toString()

    private fun saveTemporaryContent(
        title: String,
        content: String
    ): Job = writeCommunityViewModel.temporarySaveContent(title, content)

    private fun isTemporarySaveComplete(): Unit =
        writeCommunityViewModel.isSaveSuccess.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                toastMessage.apply { setText("저장이 완료되었습니다!") }.show()

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.FragmentContainer, CommunityFragment())
                    .commit()
            }
        }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.TemporarySave_Btn -> { saveTemporaryContent(title, content) }
            R.id.TemporaryLoad_Btn -> {  }
            R.id.Upload_Btn -> {  }
        }
    }

}
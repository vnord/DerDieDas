package com.github.vnord.derdiedas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.vnord.derdiedas.data.Gender
import com.github.vnord.derdiedas.databinding.FragmentNewEntryBinding

class NewEntryFragment : Fragment() {

    private var _binding: FragmentNewEntryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArticleViewModel by activityViewModels {
        ArticleViewModelFactory((activity?.application as DerDieDasApplication).dataBase.articleDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        binding.closeButton.setOnClickListener {
            val action = NewEntryFragmentDirections.actionNewEntryFragmentToStartFragment()
            findNavController().navigate(action)
        }
        binding.submitButton.setOnClickListener {
            addNewArticle()
        }

        binding.articleField.addTextChangedListener {
            binding.submitButton.isEnabled = isEntryValid()
        }

        binding.articleRadioGroup.setOnCheckedChangeListener { _, _ ->
            binding.submitButton.isEnabled = isEntryValid()
        }
    }

    private fun getSelectedGender(): Gender {
        return when (binding.articleRadioGroup.checkedRadioButtonId) {
            binding.derButton.id -> Gender.DER
            binding.dieButton.id -> Gender.DIE
            else -> Gender.DAS
        }
    }

    private fun isEntryValid(): Boolean {
        return binding.articleRadioGroup.checkedRadioButtonId != -1
                && viewModel.isEntryValid(binding.articleField.text.toString())
    }

    private fun addNewArticle() {
        viewModel.addNewArticle(
            binding.articleField.text.toString(),
            getSelectedGender()
        )
        val action = NewEntryFragmentDirections.actionNewEntryFragmentToStartFragment()
        findNavController().navigate(action)
    }
}
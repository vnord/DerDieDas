package com.github.vnord.derdiedas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.vnord.derdiedas.DerDieDasApplication
import com.github.vnord.derdiedas.NounViewModel
import com.github.vnord.derdiedas.NounViewModelFactory
import com.github.vnord.derdiedas.data.Gender
import com.github.vnord.derdiedas.databinding.FragmentNewEntryBinding

class NewEntryFragment : Fragment() {

    private var _binding: FragmentNewEntryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NounViewModel by activityViewModels {
        NounViewModelFactory((activity?.application as DerDieDasApplication).dataBase.nounDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            addNewNoun()
        }

        binding.nounField.addTextChangedListener {
            binding.submitButton.isEnabled = isEntryValid()
        }

        binding.genderRadioGroup.setOnCheckedChangeListener { _, _ ->
            binding.submitButton.isEnabled = isEntryValid()
        }
    }

    private fun getSelectedGender(): Gender {
        return when (binding.genderRadioGroup.checkedRadioButtonId) {
            binding.derButton.id -> Gender.DER
            binding.dieButton.id -> Gender.DIE
            else -> Gender.DAS
        }
    }

    private fun isEntryValid(): Boolean {
        return binding.genderRadioGroup.checkedRadioButtonId != -1
                && viewModel.isEntryValid(binding.nounField.text.toString())
    }

    private fun addNewNoun() {
        viewModel.addNewNoun(
            binding.nounField.text.toString(),
            getSelectedGender()
        )
        val action = NewEntryFragmentDirections.actionNewEntryFragmentToNounListFragment()
        findNavController().navigate(action)
    }
}
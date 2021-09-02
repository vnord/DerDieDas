package com.github.vnord.derdiedas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.github.vnord.derdiedas.DerDieDasApplication
import com.github.vnord.derdiedas.NounPhraseViewModel
import com.github.vnord.derdiedas.NounPhraseViewModelFactory
import com.github.vnord.derdiedas.R
import com.github.vnord.derdiedas.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null

    private val binding get() = _binding!!

    private var startQuizAction = StartFragmentDirections.actionStartFragmentToDoneFragment()

    private val nounPhraseViewModel: NounPhraseViewModel by activityViewModels {
        NounPhraseViewModelFactory((activity?.application as DerDieDasApplication).dataBase.nounPhraseDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            activity?.moveTaskToBack(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nounPhraseViewModel.getNumberOfEligiblePhrases().observe(this.viewLifecycleOwner) {
            if (it > 0) {
                startQuizAction = StartFragmentDirections.actionStartFragmentToQuizFragment()
            }
        }

        binding.startButton.setOnClickListener {
            findNavController().navigate(startQuizAction)
        }

        binding.listButton.setOnClickListener {
            findNavController().navigate(R.id.action_StartFragment_to_nounPhraseListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

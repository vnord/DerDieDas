package com.github.vnord.derdiedas.view

import android.graphics.Color
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
import com.github.vnord.derdiedas.data.Gender
import com.github.vnord.derdiedas.databinding.FragmentQuizBinding
import com.github.vnord.derdiedas.viewmodel.QuizViewModel
import com.github.vnord.derdiedas.viewmodel.QuizViewModelFactory

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null

    private val binding get() = _binding!!

    private val quizViewModel: QuizViewModel by activityViewModels {
        QuizViewModelFactory((activity?.application as DerDieDasApplication).dataBase.nounPhraseDao())
    }
    private val nounPhraseViewModel: NounPhraseViewModel by activityViewModels {
        NounPhraseViewModelFactory((activity?.application as DerDieDasApplication).dataBase.nounPhraseDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToStartFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        binding.quizViewModel = quizViewModel
        binding.lifecycleOwner = this
        nounPhraseViewModel.getNumberOfEligiblePhrases().observe(this.viewLifecycleOwner) {
            if (it < 1) findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToDoneFragment())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizViewModel.getRandomNounPhrase()


        val action = QuizFragmentDirections.actionQuizFragmentSelf()
        binding.derButton.apply {
            this.setOnClickListener {
                if (quizViewModel.isGenderCorrect(Gender.DER)) {
                    quizViewModel.updateNextReview()
                    findNavController().navigate(action)
                } else {
                    this.isEnabled = false
                    this.setBackgroundColor(Color.RED)
                }
            }
        }
        binding.dieButton.apply {
            this.setOnClickListener {
                if (quizViewModel.isGenderCorrect(Gender.DIE)) {
                    quizViewModel.updateNextReview()
                    findNavController().navigate(action)
                } else {
                    this.isEnabled = false
                    this.setBackgroundColor(Color.RED)
                }
            }
        }
        binding.dasButton.apply {
            this.setOnClickListener {
                if (quizViewModel.isGenderCorrect(Gender.DAS)) {
                    quizViewModel.updateNextReview()
                    findNavController().navigate(action)
                } else {
                    this.isEnabled = false
                    this.setBackgroundColor(Color.RED)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

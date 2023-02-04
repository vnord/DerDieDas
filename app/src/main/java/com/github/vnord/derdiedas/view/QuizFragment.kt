package com.github.vnord.derdiedas.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.vnord.derdiedas.DerDieDasApplication
import com.github.vnord.derdiedas.viewmodel.NounViewModel
import com.github.vnord.derdiedas.viewmodel.NounViewModelFactory
import com.github.vnord.derdiedas.data.Gender
import com.github.vnord.derdiedas.databinding.FragmentQuizBinding
import com.github.vnord.derdiedas.viewmodel.QuizViewModel
import com.github.vnord.derdiedas.viewmodel.QuizViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null

    private val binding get() = _binding!!

    private var continueQuizAction = QuizFragmentDirections.actionQuizFragmentSelf()

    private val quizViewModel: QuizViewModel by activityViewModels {
        QuizViewModelFactory((activity?.application as DerDieDasApplication).dataBase.nounDao())
    }
    private val nounViewModel: NounViewModel by activityViewModels {
        NounViewModelFactory((activity?.application as DerDieDasApplication).dataBase.nounDao())
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
        nounViewModel.getNumberOfEligibleNouns().observe(this.viewLifecycleOwner) {
            if (it < 1)  continueQuizAction = QuizFragmentDirections.actionQuizFragmentToDoneFragment()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizViewModel.getRandomNoun()

        var gotItRight = true

        binding.derButton.apply {
            this.setOnClickListener {
                if (quizViewModel.isGenderCorrect(Gender.DER)) {
                    quizViewModel.updateNextReview(gotItRight)
                    highlightCorrectButtonAndContinue()
                } else {
                    this.isEnabled = false
                    this.setBackgroundColor(Color.RED)
                    gotItRight = false
                }
            }
        }
        binding.dieButton.apply {
            this.setOnClickListener {
                if (quizViewModel.isGenderCorrect(Gender.DIE)) {
                    quizViewModel.updateNextReview(gotItRight)
                    highlightCorrectButtonAndContinue()
                } else {
                    this.isEnabled = false
                    this.setBackgroundColor(Color.RED)
                    gotItRight = false
                }
            }
        }
        binding.dasButton.apply {
            this.setOnClickListener {
                if (quizViewModel.isGenderCorrect(Gender.DAS)) {
                    quizViewModel.updateNextReview(gotItRight)
                    highlightCorrectButtonAndContinue()
                } else {
                    this.isEnabled = false
                    this.setBackgroundColor(Color.RED)
                    gotItRight = false
                }
            }
        }
        binding.alreadyKnownButton.apply {
            this.setOnClickListener {
                quizViewModel.markThisNounAsDone()
                highlightCorrectButtonAndContinue()
            }
        }
        binding.dontKnowButton.apply {
            this.setOnClickListener {
                highlightCorrectButtonAndContinue()
            }
        }
    }

    private fun highlightCorrectButtonAndContinue() {
        when (quizViewModel.noun.value?.gender) {
            Gender.DER -> binding.derButton.setBackgroundColor(correctColor)
            Gender.DIE -> binding.dieButton.setBackgroundColor(correctColor)
            Gender.DAS -> binding.dasButton.setBackgroundColor(correctColor)
            else -> {}
        }
        lifecycleScope.launch {
            disableAllButtons()
            delay(2000)
            findNavController().navigate(continueQuizAction)
        }
    }

    private val correctColor = Color.parseColor("#2e7d32")

    private fun disableAllButtons() {
        binding.root.isEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

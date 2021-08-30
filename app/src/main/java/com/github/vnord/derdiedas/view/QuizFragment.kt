package com.github.vnord.derdiedas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.vnord.derdiedas.DerDieDasApplication
import com.github.vnord.derdiedas.viewmodel.QuizViewModel
import com.github.vnord.derdiedas.viewmodel.QuizViewModelFactory
import com.github.vnord.derdiedas.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null

    private val binding get() = _binding!!

//    private val viewModel: QuizViewModel by lazy {
//        ViewModelProvider(
//            this,
//            QuizViewModelFactory((activity?.application as DerDieDasApplication).dataBase.nounPhraseDao())
//        ).get(QuizViewModel::class.java)
//    }
    private val viewModel: QuizViewModel by activityViewModels {
        QuizViewModelFactory((activity?.application as DerDieDasApplication).dataBase.nounPhraseDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        binding.quizViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRandomNounPhrase()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

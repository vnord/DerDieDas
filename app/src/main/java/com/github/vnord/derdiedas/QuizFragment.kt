package com.github.vnord.derdiedas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.vnord.derdiedas.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null

    private val binding get() = _binding!!

    private val viewModel: QuizViewModel by lazy {
        ViewModelProvider(
            this,
            QuizViewModelFactory((activity?.application as DerDieDasApplication).dataBase.articleDao())
        ).get(QuizViewModel::class.java)
    }
//    private val viewModel: QuizViewModel by activityViewModels {
//        QuizViewModelFactory((activity?.application as DerDieDasApplication).dataBase.articleDao())
//    }

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
        viewModel.getRandomArticle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

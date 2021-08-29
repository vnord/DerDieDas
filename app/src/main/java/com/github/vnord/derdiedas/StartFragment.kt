package com.github.vnord.derdiedas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.vnord.derdiedas.databinding.FragmentStartBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel: ArticleViewModel by activityViewModels {
        ArticleViewModelFactory(
            (activity?.application as DerDieDasApplication).dataBase.articleDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.articlesView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val articleAdapter = ArticleAdapter({})
        recyclerView.adapter = articleAdapter
        lifecycle.coroutineScope.launch {
            viewModel.allArticles().collect() {
                articleAdapter.submitList(it)
            }
        }

        binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_StartFragment_to_QuizFragment)
        }
        binding.newEntryButton.setOnClickListener {
            findNavController().navigate(R.id.action_StartFragment_to_NewEntryFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
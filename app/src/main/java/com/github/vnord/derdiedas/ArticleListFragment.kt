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
import com.github.vnord.derdiedas.databinding.FragmentArticleListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ArticleListFragment : Fragment() {

    private var _binding: FragmentArticleListBinding? = null

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
        _binding = FragmentArticleListBinding.inflate(inflater, container, false)
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

        binding.newEntryButton.setOnClickListener {
            findNavController().navigate(R.id.action_articleListFragment_to_NewEntryFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
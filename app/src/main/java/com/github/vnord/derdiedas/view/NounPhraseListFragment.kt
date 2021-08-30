package com.github.vnord.derdiedas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.vnord.derdiedas.*
import com.github.vnord.derdiedas.databinding.FragmentNounPhraseListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NounPhraseListFragment : Fragment() {

    private var _binding: FragmentNounPhraseListBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel: NounPhraseViewModel by activityViewModels {
        NounPhraseViewModelFactory(
            (activity?.application as DerDieDasApplication).dataBase.nounPhraseDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_nounPhraseListFragment_to_StartFragment)
        }

        callback.isEnabled = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNounPhraseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.nounPhrasesView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val nounPhraseAdapter = NounPhraseAdapter({})
        recyclerView.adapter = nounPhraseAdapter
        lifecycle.coroutineScope.launch {
            viewModel.allNounPhrases().collect() {
                nounPhraseAdapter.submitList(it)
            }
        }

        binding.newEntryButton.setOnClickListener {
            findNavController().navigate(R.id.action_nounPhraseListFragment_to_NewEntryFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
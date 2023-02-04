package com.github.vnord.derdiedas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.vnord.derdiedas.DerDieDasApplication
import com.github.vnord.derdiedas.viewmodel.NounViewModel
import com.github.vnord.derdiedas.viewmodel.NounViewModelFactory
import com.github.vnord.derdiedas.R
import com.github.vnord.derdiedas.databinding.FragmentNounListBinding
import kotlinx.coroutines.launch

class NounListFragment : Fragment() {

    private var _binding: FragmentNounListBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel: NounViewModel by activityViewModels {
        NounViewModelFactory(
            (activity?.application as DerDieDasApplication).dataBase.nounDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_nounListFragment_to_StartFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNounListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.nounsView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val nounAdapter = NounAdapter {}
        recyclerView.adapter = nounAdapter
        lifecycle.coroutineScope.launch { viewModel.allNouns().collect(nounAdapter::submitList) }

        binding.newEntryButton.setOnClickListener {
            findNavController().navigate(R.id.action_nounListFragment_to_NewEntryFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

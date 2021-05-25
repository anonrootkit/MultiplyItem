package com.example.multiplyitem.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.multiplyitem.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), OnItemClickListener {

    private lateinit var homeBinding : FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private val homeAdapter by lazy {
        HomeAdapter(requireContext(), listener = this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, HomeViewModel.Factory()).get(HomeViewModel::class.java)

        homeBinding.listView.adapter = homeAdapter

        viewModel.list.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()){
                homeAdapter.initList(it)
            }
        }
    }

    override fun onItemClick(position: Int) {
        viewModel.onItemClick(position)
    }
}

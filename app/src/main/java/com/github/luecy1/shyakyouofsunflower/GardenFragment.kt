package com.github.luecy1.shyakyouofsunflower

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.github.luecy1.shyakyouofsunflower.adapters.GardenPlantingAdapter
import com.github.luecy1.shyakyouofsunflower.databinding.FragmentGardenBinding
import com.github.luecy1.shyakyouofsunflower.utils.InjectorUtils
import com.github.luecy1.shyakyouofsunflower.viewmodel.GardenPlantingListViewModel

class GardenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGardenBinding.inflate(inflater, container, false)
        val adapter = GardenPlantingAdapter()
        binding.gardenList.adapter = adapter
        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: GardenPlantingAdapter, binding: FragmentGardenBinding) {

        val factory = InjectorUtils.provideGardenPlantingListModelFactory(requireContext())
        val viewModel = ViewModelProviders.of(this, factory)
            .get(GardenPlantingListViewModel::class.java)

        viewModel.gardenPlantings
    }
}

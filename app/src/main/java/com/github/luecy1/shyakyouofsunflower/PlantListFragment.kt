package com.github.luecy1.shyakyouofsunflower


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.luecy1.shyakyouofsunflower.adapters.PlantAdapter
import com.github.luecy1.shyakyouofsunflower.databinding.FragmentPlantListBinding
import com.github.luecy1.shyakyouofsunflower.utils.InjectorUtils
import com.github.luecy1.shyakyouofsunflower.viewmodel.PlantListViewModel

class PlantListFragment : Fragment() {

    private lateinit var viewModel: PlantListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

        val factory = InjectorUtils.providePlantListModelFactory(context)
        viewModel = ViewModelProviders.of(this, factory).get(PlantListViewModel::class.java)

        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }

    private fun subscribeUi(adapter: PlantAdapter) {
        // TODO
    }
}

package com.github.luecy1.shyakyouofsunflower

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.github.luecy1.shyakyouofsunflower.databinding.FragmentPlantDetailBinding
import com.github.luecy1.shyakyouofsunflower.utils.InjectorUtils
import com.github.luecy1.shyakyouofsunflower.viewmodel.PlantDetailViewModel
import com.google.android.material.snackbar.Snackbar


class PlantDetailFragment : Fragment() {

    private val args: PlantDetailFragmentArgs by navArgs()
    private lateinit var shareText: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = InjectorUtils.providePlantDetailViewModelFactory(
            requireActivity(), args.plantId
        )
        val plantDetailViewModel = ViewModelProviders.of(this, factory)
            .get(PlantDetailViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentPlantDetailBinding>(
            inflater, R.layout.fragment_plant_detail, container, false
        ).apply {
            viewModel = plantDetailViewModel
            lifecycleOwner = this@PlantDetailFragment
            fab.setOnClickListener { view ->
                plantDetailViewModel.addPlantToGarden()
                Snackbar.make(view, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG).show()
            }

        }

        plantDetailViewModel.plant.observe(this, Observer { plant ->
            shareText = if (plant == null) {
                ""
            } else {
                getString(R.string.shere_text_plant, plant.name)
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                val shareIntent = ShareCompat.IntentBuilder.from(activity)
                    .setText(shareText)
                    .setType("text/plain")
                    .createChooserIntent()
                    .apply {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                        } else {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                        }
                    }
                startActivity(shareIntent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

package com.zandroid.metromvi.ui.station

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.zandroid.metromvi.R
import com.zandroid.metromvi.databinding.FragmentStationBinding
import com.zandroid.metromvi.utils.network.ConnectivityStatus
import com.zandroid.metromvi.utils.network.NetworkConnectivity
import com.zandroid.metromvi.utils.setupRecyclerView
import com.zandroid.metromvi.utils.visible
import com.zandroid.metromvi.view.station.StationIntent
import com.zandroid.metromvi.view.station.StationState
import com.zandroid.metromvi.view.station.StationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class StationFragment : Fragment() {

    //Binding
    private var _binding: FragmentStationBinding?=null
    private val binding get() = _binding!!

    @Inject
    lateinit var stationAdapter: StationAdapter

    @Inject
    lateinit var connection:NetworkConnectivity

    //Other
    private val viewModel:StationViewModel by viewModels()
    private val args:StationFragmentArgs by navArgs()
    private var lineId=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentStationBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lineId=args.lineId

        binding.apply {
            lifecycleScope.launch {
                viewModel.stationIntent.send(StationIntent.LoadStations(lineId.toString()))

                viewModel.state.collect{state->
                    when(state){
                        is StationState.Loading->{
                            progress.visible(true,recyclerStations)
                            txtLoading.visible(true,recyclerStations)
                        }
                        is StationState.ShowStations->{
                            progress.visible(false,recyclerStations)
                            txtLoading.visible(false,recyclerStations)
                            checkConnection(false)
                            stationAdapter.setData(state.data)
                            recyclerStations.setupRecyclerView(LinearLayoutManager(requireContext()),stationAdapter)

                            //click
                            stationAdapter.setOnItemClickListener {
                                val direction=StationFragmentDirections.actionStationFragmentToDetailFragment(it)
                                findNavController().navigate(direction)
                            }
                        }
                        is StationState.ShowError->{
                            checkConnection(true)
                        }
                    }

                }
            }

            //check Internet
            lifecycleScope.launch {
                connection.observe().collect{
                    when (it){
                            ConnectivityStatus.Status.AVAILABLE->{ checkConnection(false)}
                            ConnectivityStatus.Status.UNAVAILABLE -> {}
                            ConnectivityStatus.Status.LOSING -> {}
                            ConnectivityStatus.Status.LOST -> { checkConnection(true) }
                        }
                    }
                }
            }

        }


    private fun checkConnection(isShownError: Boolean) {
        binding.apply {
            if (isShownError) {
                disconnectLayStation.visible(true, recyclerStations)
            } else {
                disconnectLayStation.visible(false, recyclerStations)
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}
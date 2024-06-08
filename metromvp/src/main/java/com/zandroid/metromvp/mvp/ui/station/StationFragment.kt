package com.zandroid.metromvp.mvp.ui.station

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.muddassir.connection_checker.ConnectionState
import com.muddassir.connection_checker.checkConnection
import com.zandroid.metromvp.R
import com.zandroid.metromvp.databinding.FragmentStationBinding
import com.zandroid.metromvp.mvp.data.model.Line
import com.zandroid.metromvp.mvp.data.model.ResponseStation
import com.zandroid.metromvp.mvp.ui.line.LineAdapter
import com.zandroid.metromvp.mvp.ui.line.LinePresenter
import com.zandroid.metromvp.mvp.utils.LINES
import com.zandroid.metromvp.mvp.utils.STATIONS
import com.zandroid.metromvp.mvp.utils.isNetworkAvailable
import com.zandroid.metromvp.mvp.utils.isVisible
import com.zandroid.metromvp.mvp.utils.recyclerViewApply
import com.zandroid.metromvp.mvp.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import javax.inject.Inject

@AndroidEntryPoint
class StationFragment : Fragment(),StationContracts.View {

    //Binding
    private lateinit var binding:FragmentStationBinding

    @Inject
    lateinit var stationAdapter: StationAdapter
    @Inject
    lateinit var presenter: StationPresenter

    private var lineId=0

    private val args: StationFragmentArgs by navArgs()

    private var stationList:List<ResponseStation.ResponseStationItem> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       binding= FragmentStationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState==null){
            lineId=args.lineId.toString().toInt()
            if (lineId>0){
                //call Api
                presenter.callAllStations(lineId.toString())
            }
        }else{
            stationList=savedInstanceState.getParcelableArrayList(STATIONS)?: emptyList()
            showStations(stationList)
        }



        //check Internet
        checkConnection(this) { connectionState ->
            when (connectionState) {
                ConnectionState.CONNECTED -> {
                    showInternetError(true)
                }
                else -> {
                    showInternetError(false)
                }
            }
        }

    }

    override fun showStations(data: List<ResponseStation.ResponseStationItem>) {
        this.stationList=data
       stationAdapter.setData(data)
       binding.recyclerStations.recyclerViewApply(stationAdapter, LinearLayoutManager(requireContext()))

        binding.loadingStations.isVisible(false)
        binding.recyclerStations.isVisible(true)
        //click on Item
        stationAdapter.setOnItemClickListener {
            val direction=StationFragmentDirections.actionStationFragmentToDetailFragment(it)
            findNavController().navigate(direction)
        }
    }

    override fun showLoading() {
        binding.loadingStations.isVisible(true)
        binding.recyclerStations.isVisible(false)
    }

    override fun hideLoading() {
        binding.loadingStations.isVisible(false)
        binding.recyclerStations.isVisible(true)
    }

    override fun isInternetAvailable(): Boolean {
        return requireContext().isNetworkAvailable()
    }

    override fun showInternetError(hasNet: Boolean) {
        binding.apply {
            if (!hasNet){
                disconnectLayStation.isVisible(true)
                stationContentLay.isVisible(false)
            }else{
                disconnectLayStation.isVisible(false)
                stationContentLay.isVisible(true)
            }
        }
    }

    override fun showServerError(err: String) {
        binding.root.showSnackBar(err)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(STATIONS, ArrayList(stationList))
    }

}
package com.zandroid.metromvi.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zandroid.metromvi.R
import com.zandroid.metromvi.databinding.FragmentDetailBinding
import com.zandroid.metromvi.utils.facilitiesIncluded
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    //Binding
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private var stationId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //back click
            btnBack.setOnClickListener { findNavController().popBackStack() }

            stationId = args.station.id!!.toInt()
            txtStationName.text = args.station.title
            txtStationAddress.text = "آدرس | ${args.station.addr} "

            //change color fun
            changeBgColor()
            if (args.station.crossLineID == "0") {
                txtCross.text = getString(R.string.noCross)
            } else {
                txtCross.text = getString(R.string.hasCross)
            }
        }
    }

    private fun changeBgColor(){
        binding.apply {
            when(args.station.lineId){
                "1" ->{ setStationColors(R.color.bg_tajrish)}

                "2" ->{ setStationColors(R.color.Tehranpars) }

                "3" ->{ setStationColors(R.color.bg_qaem) }

                "4" ->{ setStationColors(R.color.bg_eram) }

                "5" ->{ setStationColors(R.color.bg_sadeghiye) }

                "7" ->{ setStationColors(R.color.bg_basij) }
            }
        }

    }


    private fun Fragment.setStationColors(colorResId: Int) {
        val args=args.station
        val color = ContextCompat.getColor(requireContext(), colorResId)
        binding.apply {
            btnBack.setColorFilter(color)
            titleLay.setBackgroundColor(color)

            imgTaxi.facilitiesIncluded(args.taxi!!,color)
            imgBus.facilitiesIncluded(args.bus!!, color)
            imgParking.facilitiesIncluded(args.parking!!, color)
            imgElevator.facilitiesIncluded(args.elevator!!, color)
            imgTicket.facilitiesIncluded(args.ticket!!, color)
            imgEsclator.facilitiesIncluded(args.escalator!!, color)
            imgPhone.facilitiesIncluded(args.phone!!, color)
            imgAtm.facilitiesIncluded(args.atm!!, color)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

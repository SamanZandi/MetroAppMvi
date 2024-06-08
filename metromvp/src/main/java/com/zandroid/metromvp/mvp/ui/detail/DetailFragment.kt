package com.zandroid.metromvp.mvp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zandroid.metromvp.R
import com.zandroid.metromvp.databinding.FragmentDetailBinding
import com.zandroid.metromvp.databinding.FragmentLineBinding
import com.zandroid.metromvp.mvp.base.BasePresenterImpl
import com.zandroid.metromvp.mvp.base.BaseView
import com.zandroid.metromvp.mvp.data.model.ResponseStation
import com.zandroid.metromvp.mvp.utils.facilitiesIncluded
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment(){

    //Binding
    private lateinit var binding: FragmentDetailBinding

    //Other
    private val args: DetailFragmentArgs by navArgs()
    private var stationId=0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            //back click
            btnBack.setOnClickListener { findNavController().popBackStack() }

            stationId=args.stationInfo.id!!.toInt()
           txtStationName.text=args.stationInfo.title
           txtStationAddress.text="آدرس | ${args.stationInfo.addr} "

            //change color fun
            changeBgColor()
            if(args.stationInfo.crossLineID=="0"){
                txtCross.text= getString(R.string.noCross)
            }else{
                txtCross.text= getString(R.string.hasCross)
            }

        }
    }

    private fun changeBgColor(){
        binding.apply {
            when(args.stationInfo.lineId){
                1.toString() ->{
                    btnBack.setColorFilter(ContextCompat.getColor(requireContext(),R.color.bg_tajrish))
                    titleLay.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.bg_tajrish))
                    args.stationInfo.taxi?.let { imgTaxi.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_tajrish)) }
                    args.stationInfo.bus?.let { imgBus.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_tajrish)) }
                    args.stationInfo.parking?.let { imgParking.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_tajrish)) }
                    args.stationInfo.elevator?.let { imgElevator.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_tajrish)) }
                    args.stationInfo.ticket?.let { imgTicket.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_tajrish)) }
                    args.stationInfo.escalator?.let { imgEsclator.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_tajrish)) }
                    args.stationInfo.phone?.let { imgPhone.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_tajrish)) }
                    args.stationInfo.atm?.let { imgAtm.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_tajrish)) }
                }

                2.toString() ->{
                    btnBack.setColorFilter(ContextCompat.getColor(requireContext(),R.color.purple_700))
                    titleLay.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.purple_700))
                    args.stationInfo.taxi?.let { imgTaxi.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.purple_700)) }
                    args.stationInfo.bus?.let { imgBus.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.purple_700)) }
                    args.stationInfo.parking?.let { imgParking.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.purple_700)) }
                    args.stationInfo.elevator?.let { imgElevator.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.purple_700)) }
                    args.stationInfo.ticket?.let { imgTicket.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.purple_700)) }
                    args.stationInfo.escalator?.let { imgEsclator.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.purple_700)) }
                    args.stationInfo.phone?.let { imgPhone.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.purple_700)) }
                    args.stationInfo.atm?.let { imgAtm.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.purple_700)) }
                }

                3.toString() ->{
                    btnBack.setColorFilter(ContextCompat.getColor(requireContext(),R.color.bg_qaem))
                    titleLay.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.bg_qaem))
                    args.stationInfo.taxi?.let { imgTaxi.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_qaem)) }
                    args.stationInfo.bus?.let { imgBus.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_qaem)) }
                    args.stationInfo.parking?.let { imgParking.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_qaem)) }
                    args.stationInfo.elevator?.let { imgElevator.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_qaem)) }
                    args.stationInfo.ticket?.let { imgTicket.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_qaem)) }
                    args.stationInfo.escalator?.let { imgEsclator.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_qaem)) }
                    args.stationInfo.phone?.let { imgPhone.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_qaem)) }
                    args.stationInfo.atm?.let { imgAtm.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_qaem)) }
                }

                4.toString() ->{
                    btnBack.setColorFilter(ContextCompat.getColor(requireContext(),R.color.bg_eram))
                    titleLay.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.bg_eram))
                    args.stationInfo.taxi?.let { imgTaxi.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_eram)) }
                    args.stationInfo.bus?.let { imgBus.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_eram)) }
                    args.stationInfo.parking?.let { imgParking.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_eram)) }
                    args.stationInfo.elevator?.let { imgElevator.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_eram)) }
                    args.stationInfo.ticket?.let { imgTicket.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_eram)) }
                    args.stationInfo.escalator?.let { imgEsclator.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_eram)) }
                    args.stationInfo.phone?.let { imgPhone.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_eram)) }
                    args.stationInfo.atm?.let { imgAtm.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_eram)) }
                }

                5.toString() ->{
                    btnBack.setColorFilter(ContextCompat.getColor(requireContext(),R.color.bg_sadeghiye))
                    titleLay.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.bg_sadeghiye))
                    args.stationInfo.taxi?.let { imgTaxi.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_sadeghiye)) }
                    args.stationInfo.bus?.let { imgBus.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_sadeghiye)) }
                    args.stationInfo.parking?.let { imgParking.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_sadeghiye)) }
                    args.stationInfo.elevator?.let { imgElevator.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_sadeghiye)) }
                    args.stationInfo.ticket?.let { imgTicket.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_sadeghiye)) }
                    args.stationInfo.escalator?.let { imgEsclator.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_sadeghiye)) }
                    args.stationInfo.phone?.let { imgPhone.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_sadeghiye)) }
                    args.stationInfo.atm?.let { imgAtm.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_sadeghiye)) }
                }

                7.toString() ->{
                    btnBack.setColorFilter(ContextCompat.getColor(requireContext(),R.color.bg_basij))
                    titleLay.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.bg_basij))
                    args.stationInfo.taxi?.let { imgTaxi.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_basij)) }
                    args.stationInfo.bus?.let { imgBus.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_basij)) }
                    args.stationInfo.parking?.let { imgParking.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_basij)) }
                    args.stationInfo.elevator?.let { imgElevator.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_basij)) }
                    args.stationInfo.ticket?.let { imgTicket.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_basij)) }
                    args.stationInfo.escalator?.let { imgEsclator.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_basij)) }
                    args.stationInfo.phone?.let { imgPhone.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_basij)) }
                    args.stationInfo.atm?.let { imgAtm.facilitiesIncluded(it,
                        ContextCompat.getColor(requireContext(),R.color.bg_basij)) }
                }
            }
        }
    }



}
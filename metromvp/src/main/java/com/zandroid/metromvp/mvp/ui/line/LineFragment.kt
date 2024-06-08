package com.zandroid.metromvp.mvp.ui.line

import android.R.string
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muddassir.connection_checker.ConnectionState
import com.muddassir.connection_checker.checkConnection
import com.zandroid.metromvp.databinding.FragmentLineBinding
import com.zandroid.metromvp.mvp.data.model.Line
import com.zandroid.metromvp.mvp.utils.LINES
import com.zandroid.metromvp.mvp.utils.isNetworkAvailable
import com.zandroid.metromvp.mvp.utils.isVisible
import com.zandroid.metromvp.mvp.utils.recyclerViewApply
import com.zandroid.metromvp.mvp.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import javax.inject.Inject


@AndroidEntryPoint
class LineFragment : Fragment(),LineContracts.View {
    //Binding
    private lateinit var binding:FragmentLineBinding

    @Inject
    lateinit var lineAdapter: LineAdapter

    @Inject
    lateinit var presenter: LinePresenter

    private var lineList:List<Line.ResponseLinesItem> = emptyList()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      binding= FragmentLineBinding.inflate(layoutInflater)
        return binding.root


    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       if (savedInstanceState==null){
            presenter.callAllLines()
        }else{
            lineList=savedInstanceState.getParcelableArrayList(LINES)?: emptyList()
            showLines(lineList)
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
     //   presenter.callAllLines()
      //  savedInstanceState?.get(LINES)
    }

    override fun showLines(lines: List<Line.ResponseLinesItem>) {
        this.lineList=lines
        lineAdapter.setData(lines)

      /*  binding.recyclerLines.apply {
            adapter=lineAdapter
            layoutManager=LinearLayoutManager(requireContext())
        }*/

        binding.recyclerLines.recyclerViewApply(lineAdapter,LinearLayoutManager(requireContext()))
        binding.progress.isVisible(false)
        binding.recyclerLines.isVisible(true)

        //click
        lineAdapter.setOnItemClickListener {
            val direction=LineFragmentDirections.actionLineFragmentToStationFragment(it.id.toInt())
            findNavController().navigate(direction)
        }
    }


    override fun showLoading() {
        binding.progress.isVisible(true)
        binding.recyclerLines.isVisible(false)

    }

    override fun hideLoading() {
        binding.progress.isVisible(false)
        binding.recyclerLines.isVisible(true)
    }

    override fun isInternetAvailable(): Boolean {
        return requireContext().isNetworkAvailable()
    }

    override fun showInternetError(hasNet: Boolean) {
            binding.apply {
                if (!hasNet){
                    disconnectLay.isVisible(true)
                    contentLay.isVisible(false)
                }else{
                    disconnectLay.isVisible(false)
                    contentLay.isVisible(true)
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
        outState.putParcelableArrayList(LINES, ArrayList(lineList))
       // outState.putParcelable(LINES, binding.recyclerLines.layoutManager?.onSaveInstanceState())
    }
}
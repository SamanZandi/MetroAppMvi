package com.zandroid.metromvi.ui.line

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zandroid.metromvi.databinding.FragmentLineBinding
import com.zandroid.metromvi.utils.network.ConnectivityStatus
import com.zandroid.metromvi.utils.network.NetworkConnectivity
import com.zandroid.metromvi.utils.setupRecyclerView
import com.zandroid.metromvi.utils.visible
import com.zandroid.metromvi.view.line.LineIntent
import com.zandroid.metromvi.view.line.LineState
import com.zandroid.metromvi.view.line.LineViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LineFragment : Fragment() {

    //Binding
    private var _binding: FragmentLineBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var lineAdapter: LineAdapter

    @Inject
    lateinit var connection: NetworkConnectivity

    //Other
    private val viewModel: LineViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLineBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //send on channel
        lifecycleScope.launch { viewModel.lineIntent.send(LineIntent.LoadLineList) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            //get data
            lifecycleScope.launch {
                viewModel.state.collect { state ->
                    when (state) {
                        is LineState.Loading -> {
                            progress.visible(true, recyclerLines)
                            txtLoading.visible(true, recyclerLines)
                        }

                        is LineState.ShowLineList -> {
                            checkConnection(false)
                            progress.visible(false, recyclerLines)
                            txtLoading.visible(false, recyclerLines)
                            lineAdapter.setData(state.lines)
                            recyclerLines.setupRecyclerView(LinearLayoutManager(requireContext()), lineAdapter)

                            //click
                            lineAdapter.setOnItemClickListener {
                                val direction=LineFragmentDirections.actionLineFragmentToStationFragment(it.id.toInt())
                                findNavController().navigate(direction)
                            }
                        }

                        is LineState.ShowError -> {
                            checkConnection(true)
                        }
                    }
                }
            }

            //check Internet
            lifecycleScope.launch {
                connection.observe().collect {
                    when (it) {
                        ConnectivityStatus.Status.AVAILABLE -> {
                            checkConnection(false)
                        }
                        ConnectivityStatus.Status.UNAVAILABLE -> {}
                        ConnectivityStatus.Status.LOSING -> {}
                        ConnectivityStatus.Status.LOST -> {
                            checkConnection(true)
                        }
                    }
                }
            }

        }
    }


    private fun checkConnection(isShownError: Boolean) {
        binding.apply {
            if (isShownError) {
                disconnectLay.visible(true, recyclerLines)
            } else {
                disconnectLay.visible(false, recyclerLines)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
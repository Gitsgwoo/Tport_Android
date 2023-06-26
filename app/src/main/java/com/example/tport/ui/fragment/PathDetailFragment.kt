package com.example.tport.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tport.MapFragmentActivity
import com.example.tport.MyApplication
import com.example.tport.databinding.FragmentPathDetailBinding
import com.example.tport.network.dto.previous.Path0
import com.example.tport.ui.PathFindingViewModel
import com.example.tport.ui.PathFindingViewModelFactory
import com.example.tport.ui.adapter.MethodListAdapter


class PathDetailFragment : Fragment() {

    lateinit var mainActivity: MapFragmentActivity
    lateinit var path: Path0
    private var _binding: FragmentPathDetailBinding? = null
    private val binding get() = _binding!!
    private val navigationArgs: PathDetailFragmentArgs by navArgs()
    private val viewModel: PathFindingViewModel by activityViewModels {
        PathFindingViewModelFactory(
            (activity?.application as MyApplication).database.pathDao(),
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MapFragmentActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPathDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.retrievePath(navigationArgs.pathId).observe(this.viewLifecycleOwner){
            bind(it)
            path = it
            var methodList = viewModel.getMethodList(it)
            val adapter = MethodListAdapter(
                onItemClicked = {

                },
                onButtonClicked = {
                    updateReservedData(path)
                    methodList = viewModel.getMethodList(path)
                }
            )
            binding.recyclerview.adapter = adapter
            binding.recyclerview.layoutManager = LinearLayoutManager(this.context)
            adapter.submitList(methodList)
        }
        binding.upButton.setOnClickListener{
            findNavController().navigateUp()
        }

    }

    fun bind(path: Path0){
        val hourArrival = path.tportArrivalTime/60
        val minArrival = path.tportArrivalTime%60
        val timeArrival = hourArrival.toString() + "시 " + minArrival.toString() + "분"
        val listArrivalTime: List<String> = listOf("ㅣ", timeArrival, "도착", "ㅣ")
        val hourTravel = path.tportTravelTime/60
        val minTravel = path.tportTravelTime%60
        val timeTravel = hourTravel.toString() + "시간 " + minTravel.toString() + "분"
        binding.apply {
            totalTravelTime.text = timeTravel
            finalArrivalTime.text = listArrivalTime.joinToString(" ")
            fare.text = path.fare
            travelSequence.text = concatenateSequence(
                path.method1, path.travelTime1, path.method2, path.travelTime2, path.method3,
                path.travelTime3, path.method4, path.travelTime4, path.method5, path.travelTime5,
                path.method6, path.travelTime6
            )
            arrivalTime.text = timeArrival
            destination.text = path.destination
        }
    }

    private fun concatenateSequence(
        s1: String, s2: String, s3: String, s4: String, s5: String, s6: String,
        s7: String, s8: String, s9: String, s10: String, s11: String, s12: String,
    ): String {
        val input = listOf(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12)
        val output: MutableList<String> = mutableListOf()
        for (i in input.indices) {
            if (input[i] != "None" && input[i] != "NoneNone" && input[i] != "" ) {

                if (i%2 == 1) {
                    output.add(input[i-1]+" "+input[i]+"분")
                }
            }
        }
        return output.joinToString("  →  ")
    }

    private fun updateReservedData(path: Path0) {
        viewModel.updateReservedNum(path.id)
    }
}
package com.example.tport.ui.fragment

import DialogDatePickerOnlyDay
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tport.MapFragmentActivity
import com.example.tport.MyApplication
import com.example.tport.databinding.FragmentPathFindingBinding
import com.example.tport.ui.PathFindingViewModel
import com.example.tport.ui.PathFindingViewModelFactory
import com.example.tport.ui.adapter.NaverPathListAdapter
import com.example.tport.ui.adapter.TportPathListAdapter
import java.util.*


class PathFindingFragment : Fragment() {

    lateinit var mainActivity: MapFragmentActivity
    private var _binding: FragmentPathFindingBinding? = null
    private val binding get() = _binding!!
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
        _binding = FragmentPathFindingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val naverAdapter = NaverPathListAdapter(
            onItemClicked = {
                Log.d("PathFindingFragment", "Path is clicked")
                navigateToDetail(it.id)
            }
        )
        val tportAdapter = TportPathListAdapter(
            onItemClicked = {
                navigateToDetail(it.id)
            }
        )

        // 초기 adapter는 naverAdapter로 설정
        binding.recyclerview.adapter = naverAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this.context)

//        binding.timeButton.setOnClickListener{
//            // recyclerView에 naverAdapter 연결
//            binding.recyclerview.adapter = naverAdapter
//            viewModel.getSearchedPathList(binding.editTextOrgin.text.toString(), binding.editTextDestination.text.toString(), "6시 45분")
////            viewModel.getSearchedPathList("경기도 화성시 청계동 KCC스위첸아파트", "서울역1호선", "6시 45분")
//            viewModel.searchedPathList.observe(this.viewLifecycleOwner) {
//                viewModel.searchedPathList.let { naverAdapter.submitList( it.value) }
//            }
//        }

        var dateString = ""
        var timeString = ""
        var timeResult = ""

        binding.dateButton.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                dateString = "${year}년 ${month+1}월 ${dayOfMonth}일"
                timeResult = "날짜/시간 : "+ dateString + " / " + timeString
            }
            val datePickerDialog: DatePickerDialog = DialogDatePickerOnlyDay(mainActivity,
                dateSetListener, year, month, day)
            datePickerDialog.show()
        }

        binding.timeButton.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeString = "${hourOfDay}시 ${minute}분"
                timeResult = "날짜/시간 : "+dateString + " / " + timeString
            }
            TimePickerDialog(mainActivity, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true).show()
        }

//        binding.dateButton.setOnClickListener{
//            // recyclerView에 tportAdapter 연결
//            binding.recyclerview.adapter = tportAdapter
//            viewModel.getTportSearchedPathList(binding.editTextOrgin.text.toString(), binding.editTextDestination.text.toString(), "6시 45분")
////            viewModel.getTportSearchedPathList("경기도 화성시 청계동 KCC스위첸아파트", "서울역1호선", "6시 45분")
//            viewModel.tportSearchedPathList.observe(this.viewLifecycleOwner) {
//                viewModel.tportSearchedPathList.let { tportAdapter.submitList( it.value) }
//            }
//        }
    }

    private fun navigateToDetail(id: Int) {
        val action = PathFindingFragmentDirections.actionPathFindingFragmentToPathDetailFragment(id)
        this.findNavController().navigate(action)
    }
}
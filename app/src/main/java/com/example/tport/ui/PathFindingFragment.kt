package com.example.tport.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tport.MapFragmentActivity
import com.example.tport.MyApplication
import com.example.tport.data.Path
import com.example.tport.databinding.FragmentPathFindingBinding
import com.example.tport.util.ExtractData
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet

class PathFindingFragment : Fragment() {

    lateinit var mainActivity: MapFragmentActivity
    lateinit var extractData: ExtractData
    private var _binding: FragmentPathFindingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PathFindingViewModel by activityViewModels {
        PathFindingViewModelFactory(
            (activity?.application as MyApplication).database.pathDao(),
            extractData
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MapFragmentActivity
        extractData = ExtractData(mainActivity)
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

        binding.naverSequence.setOnClickListener{
            // recyclerView에 naverAdapter 연결
            binding.recyclerview.adapter = naverAdapter
            viewModel.getSearchedPathList(binding.editTextOrgin.text.toString(), binding.editTextDestination.text.toString(), "6시 45분")
//            viewModel.getSearchedPathList("경기도 화성시 청계동 KCC스위첸아파트", "서울역1호선", "6시 45분")
            viewModel.searchedPathList.observe(this.viewLifecycleOwner) {
                viewModel.searchedPathList.let { naverAdapter.submitList( it.value) }
            }
        }

        binding.tportSequence.setOnClickListener{
            // recyclerView에 tportAdapter 연결
            binding.recyclerview.adapter = tportAdapter
            viewModel.getTportSearchedPathList(binding.editTextOrgin.text.toString(), binding.editTextDestination.text.toString(), "6시 45분")
//            viewModel.getTportSearchedPathList("경기도 화성시 청계동 KCC스위첸아파트", "서울역1호선", "6시 45분")
            viewModel.tportSearchedPathList.observe(this.viewLifecycleOwner) {
                viewModel.tportSearchedPathList.let { tportAdapter.submitList( it.value) }
            }
        }
    }

    private fun navigateToDetail(id: Int) {
        val action = PathFindingFragmentDirections.actionPathFindingFragmentToPathDetailFragment(id)
        this.findNavController().navigate(action)
    }

}
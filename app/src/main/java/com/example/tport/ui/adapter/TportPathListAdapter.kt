package com.example.tport.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tport.network.dto.previous.Path0
import com.example.tport.databinding.PathListItemBinding

class TportPathListAdapter(
    private val onItemClicked:(Path0) -> Unit
): ListAdapter<Path0, TportPathListAdapter.PathViewHolder>(DiffCallback) {

    class PathViewHolder(private val binding: PathListItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        private fun concatenate(
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
            return output.joinToString(" → ")
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
                val waitingConditionText = if (path.waitingNum <= path.emptyNum / 2) {
                    "여유"
                } else if (path.emptyNum / 2 < path.waitingNum && path.waitingNum <= path.emptyNum) {
                    "혼잡"
                } else {
                    "포화"
                }
                waitingCondition.text = waitingConditionText
                when (waitingCondition.text) {
                    "여유" -> {
                        waitingCondition.setTextColor(Color.parseColor("#00FF00")) // 연두색
                    }
                    "혼잡" -> {
                        waitingCondition.setTextColor(Color.parseColor("#FF7F00")) // 주황색
                    }
                    else -> {
                        waitingCondition.setTextColor(Color.parseColor("#FF0000")) // 빨간색
                    }
                }

                totalTravelTime.text = timeTravel
                finalArrivalTime.text = listArrivalTime.joinToString(" ")
                fare.text = path.fare
                travelSequence.text = concatenate(
                    path.method1, path.travelTime1, path.method2, path.travelTime2, path.method3, path.travelTime3,
                    path.method4, path.travelTime4, path.method5, path.travelTime5, path.method6, path.travelTime6
                )
            }
        }
    }



    private var _viewBinding: PathListItemBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathViewHolder {
        _viewBinding = PathListItemBinding.inflate(LayoutInflater.from(parent.context))
        return PathViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: PathViewHolder, position: Int) {
        val current = getItem(position)

        holder.bind(current)

        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Path0>() {
            override fun areItemsTheSame(oldItem: Path0, newItem: Path0): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Path0, newItem: Path0): Boolean {
                return oldItem == newItem
            }
        }
    }

}
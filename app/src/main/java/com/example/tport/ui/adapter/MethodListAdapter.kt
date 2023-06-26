package com.example.tport.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tport.network.dto.previous.MethodDTO
import com.example.tport.databinding.MethodListItemBinding

class MethodListAdapter(
    private val onItemClicked:(MethodDTO) -> Unit, private val onButtonClicked:(MethodDTO) -> Unit
): ListAdapter<MethodDTO, MethodListAdapter.MethodDTOViewHolder>(DiffCallback) {

    class MethodDTOViewHolder(private val binding: MethodListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        val button = binding.reserveButton


        fun bind(methodDTO: MethodDTO){
            binding.apply {
                val token = methodDTO.methodName.chunked(2)
                if(token[0] == "버스") {
                    method.text = "버스"
                } else {
                    method.text = methodDTO.methodName
                }
                val listTravelTime = listOf(methodDTO.travelTime, "분")
                travelTime.text = listTravelTime.joinToString("")
                startPoint.text = methodDTO.startPoint

                button.visibility = View.GONE
                if(methodDTO.emptyNum != -1 && methodDTO.waitingNum != -1) {
                    button.visibility = View.VISIBLE
                    val waitingConditionText = if (methodDTO.waitingNum <= methodDTO.emptyNum / 2) {
                        "여유"
                    } else if (methodDTO.emptyNum / 2 < methodDTO.waitingNum && methodDTO.waitingNum <= methodDTO.emptyNum) {
                        "혼잡"
                    } else {
                        "포화"
                    }
                    val emptyNumAndText =
                        "빈자리수 " + methodDTO.emptyNum.toString() + "석" + " / " + "대기인원 "
                    val waitingTimeText = "대기시간 " + methodDTO.waitingTime.toString() + "분"
                    val reservedNumText = "예약인원 " + methodDTO.reservedNum.toString() + "명"
                    emptyNumAnd.text = emptyNumAndText
                    waitingTime.text = waitingTimeText
                    waitingCondition.text = waitingConditionText
                    reservedNum.text = reservedNumText
                    waitingBus.text = methodDTO.methodName
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
                }

            }
        }
    }

    private var _viewBinding: MethodListItemBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MethodDTOViewHolder {
        _viewBinding = MethodListItemBinding.inflate(LayoutInflater.from(parent.context))
        return MethodDTOViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MethodDTOViewHolder, position: Int) {
        val current = getItem(position)

        holder.bind(current)

        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.button.setOnClickListener {
            onButtonClicked(current)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<MethodDTO>() {
            override fun areItemsTheSame(oldItem: MethodDTO, newItem: MethodDTO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MethodDTO, newItem: MethodDTO): Boolean {
                return oldItem == newItem
            }
        }
    }

}
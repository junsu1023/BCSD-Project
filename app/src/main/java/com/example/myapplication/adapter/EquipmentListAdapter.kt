package com.example.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.EquipmentData
import com.example.myapplication.activity.RentalActivity
import com.example.myapplication.databinding.EquipmentItemBinding

class EquipmentListAdapter(equipmentList: MutableList<EquipmentData>): RecyclerView.Adapter<EquipmentListAdapter.ViewHolder>() {
    lateinit var onClickListener: OnClickListener
    lateinit var onLongClickListener: OnLongClickListener
    private val equipmentItem = equipmentList

    class ViewHolder(private val binding: EquipmentItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(equipmentItem: EquipmentData) {
            binding.equipment = equipmentItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EquipmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    = holder.bind(equipmentItem[position])

    override fun getItemCount(): Int = equipmentItem.size

    interface OnClickListener {
        fun onClick(position: Int)
    }

    interface OnLongClickListener {
        fun onLongClick(position: Int)
    }

    inline fun setOnClickListener(crossinline item: (Int) -> Unit) {
        this.onClickListener = object: OnClickListener {
            override fun onClick(position: Int) {
                item(position)
            }
        }
    }

    inline fun setOnLongClickListener(crossinline item: (Int) -> Unit) {
        this.onLongClickListener = object: OnLongClickListener {
            override fun onLongClick(position: Int) {
                item(position)
            }
        }
    }
}
package com.lalabib.latihan.simpleproduct.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lalabib.latihan.simpleproduct.data.local.entity.OrderEntity
import com.lalabib.latihan.simpleproduct.databinding.ItemOrderBinding
import com.lalabib.latihan.simpleproduct.utils.SharedObject.loadImage
import java.text.NumberFormat
import java.util.Locale

class OrderAdapter : ListAdapter<OrderEntity, OrderAdapter.ViwHolder>(OrderDiffUtil) {
    object OrderDiffUtil : DiffUtil.ItemCallback<OrderEntity>() {
        override fun areItemsTheSame(oldItem: OrderEntity, newItem: OrderEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OrderEntity, newItem: OrderEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViwHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViwHolder(binding)
    }

    override fun onBindViewHolder(holder: ViwHolder, position: Int) {
        val order = getItem(position)
        if (order != null) {
            holder.bind(order)
        }
    }

    class ViwHolder (
        private val binding: ItemOrderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: OrderEntity) {
            binding.apply {
                val locale = Locale("id", "ID")
                val formatter = NumberFormat.getNumberInstance(locale)
                formatter.maximumFractionDigits = 3
                val formattedPrice = "Rp" + formatter.format(order.price)
                val formattedSumPrice = "Rp" + formatter.format(order.sumPrice)

                tvName.text = order.name
                tvNote.text = order.note
                tvSumItem.text = order.quantity.toString()
                tvPrice.text = formattedPrice
                tvSumPrice.text = formattedSumPrice
                loadImage(ivImage, order.image)
            }
        }
    }
}
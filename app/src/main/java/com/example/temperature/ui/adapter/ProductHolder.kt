package com.example.temperature.ui.adapter

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.temperature.R
import com.example.temperature.databinding.ProductItemBinding
import com.example.temperature.temp.repository.model.ProductItem

class ProductHolder(view: View, binding: ProductItemBinding) : RecyclerView.ViewHolder(view) {
    val _binding = binding

    fun bindData(item: ProductItem, holder: ProductHolder, status: Boolean) {
        holder._binding.productId.text = item.productId
        holder._binding.ProductDesc.text = item.productImage
        if (status) {
            holder._binding.root.setBackgroundColor(
                ContextCompat.getColor(
                    _binding.root.context,
                    R.color.purple_200
                )
            )
        } else {
            holder._binding.root.setBackgroundColor(
                ContextCompat.getColor(
                    _binding.root.context,
                    R.color.grey
                )
            )
        }
    }

    interface SwipeToDeleteCallback {
        fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
    }
}
package com.example.temperature.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.temperature.OnSwipeTouchListener
import com.example.temperature.databinding.ProductItemBinding
import com.example.temperature.temp.repository.model.ProductItem

class productAdapter(
    private val listener: SwipeToDeleteCallback
) :
    RecyclerView.Adapter<ProductHolder>(), OnSwipeTouchListener.OnSwipeEvent {
    lateinit var _binding: ProductItemBinding
    var listProduct: List<ProductItem> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        _binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(_binding.root, _binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        OnSwipeTouchListener(holder._binding.root, this, position)
        holder.bindData(listProduct[position], holder, (position % 2) == 1)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    fun updateData(data: List<ProductItem>) {
        listProduct = data
        notifyDataSetChanged()
    }

    interface SwipeToDeleteCallback {
        fun onSwiped(productId: String)
    }

    override fun SwipeEventDetected(
        v: View,
        SwipeType: OnSwipeTouchListener.SwipeTypeEnum,
        position: Int
    ) {
        listener.onSwiped(listProduct[position].productId)
    }
}
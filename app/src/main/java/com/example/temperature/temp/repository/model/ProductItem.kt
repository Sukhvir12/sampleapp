package com.example.temperature.temp.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val product: List<ProductItem>
): Parcelable

@Parcelize
data class ProductItem(
    val price: String,
    val productId: String,
    val productImage: String,
    val productName: String
) : Parcelable
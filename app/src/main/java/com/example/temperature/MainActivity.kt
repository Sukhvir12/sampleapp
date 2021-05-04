package com.example.temperature

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temperature.ViewModel.ProductViewModel
import com.example.temperature.ViewModel.TempratureViewModel
import com.example.temperature.databinding.ActivityMainBinding
import com.example.temperature.ui.adapter.productAdapter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), productAdapter.SwipeToDeleteCallback {
    lateinit var _binding: ActivityMainBinding
    lateinit var adapter: productAdapter

    lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(_binding.root)

        val tempViewModel = ViewModelProvider(this).get(TempratureViewModel::class.java)

        tempViewModel.getProducts(this)

        tempViewModel.temprature.observe(this, Observer {
            _binding.textFeelTemp.text = it.temp.toString()
        })

        initAdapter()

        initProductViewModel()

        solution(intArrayOf(5, 7, 10, 1, 13, 2, 5, 6, 3, 4, 9, 12))
        solution(intArrayOf(5, 7, 10, 1, 13, 4, 2, 5, 6, 3, 9, 12,8))
    }

    // you can also use imports, for example:
// import kotlin.math.*

// you can write to stdout for debugging purposes, e.g.
// println("this is a debug message")

    fun solution(A: IntArray): Int {
        // write your code in Kotlin
        // write your code in Kotlin
        // val arrVals = arrayOf(5, 7, 10, 1, 13, 2, 5, 6, 3, 4, 9, 12)
        val distinctArray = A.distinct().toIntArray()
        distinctArray.sort()
        val max = getMax(distinctArray)
        Log.d("solution", "==max==$max")
        if (max < 1)
            return 1
        else {
            val temp = compareMiddleVal(distinctArray)
            Log.d("solution", "==temp===$temp")
            return temp
        }
    }

    fun getMax(A: IntArray): Int {
        var max = Int.MIN_VALUE
        for (i in A) {
            max = max.coerceAtLeast(i)
        }
        return max
    }

    fun compareMiddleVal(arrVals: IntArray): Int {
        val size = arrVals.size
        var returnVal = 0
        if (size > 3) {
            Log.d("solution", "==size==$size===" + arrVals.joinToString(" "))
            val middleIndex = (size / 2)
            val temp = arrVals[middleIndex]
            val diff = arrVals[middleIndex] - arrVals[0]
            Log.d("solution", "=diff=$diff===before if===$middleIndex == $temp")

            if (middleIndex == diff) {
                Log.d("solution", "==middleIndex == arrVals[middleIndex]")
                returnVal = compareMiddleVal(arrVals.copyOfRange(middleIndex, size))
            } else {
                Log.d("solution", "else ==middleIndex == arrVals[middleIndex]")
                returnVal = compareMiddleVal(arrVals.copyOfRange(0, middleIndex))
            }
        } else {
            Log.d("solution", "else =size<2==" + arrVals.joinToString(" "))
            if (arrVals[1] + 2 == arrVals[2]) {
                returnVal = arrVals[1] + 1
            } else {
                returnVal = arrVals[1] - 1
            }
        }
        return returnVal
    }

    private fun initAdapter() {
        adapter = productAdapter(this)
        _binding.productAdapter.adapter = adapter
        _binding.productAdapter.layoutManager =
            LinearLayoutManager(this)
    }

    private fun initProductViewModel() {
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.getProducts()

        productViewModel.productLiveData.observe(this, Observer { thisData ->
            adapter.updateData(thisData)
        })
    }

    override fun onSwiped(productId: String) {
        productViewModel.deleteProduct(productId)
    }
}
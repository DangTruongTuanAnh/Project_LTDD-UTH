package com.example.callapi.ui.screen.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.callapi.data.model.Product
import com.example.callapi.data.remote.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class ProductViewModel : ViewModel() {

    private val _product = mutableStateOf<Product?>(null)
    val product: State<Product?> = _product

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _product.value = RetrofitInstance.api.getProduct()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}

package com.lalabib.latihan.simpleproduct.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lalabib.latihan.simpleproduct.data.local.entity.OrderEntity
import com.lalabib.latihan.simpleproduct.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val productUseCase: ProductUseCase) :
    ViewModel() {

    fun getProductById(id: String) = productUseCase.getProductById(id).asLiveData()

    val getUser = productUseCase.getUser().asLiveData()

    fun insertOrder(order: OrderEntity) {
        viewModelScope.launch {
            productUseCase.insertOrder(order)
        }
    }
}
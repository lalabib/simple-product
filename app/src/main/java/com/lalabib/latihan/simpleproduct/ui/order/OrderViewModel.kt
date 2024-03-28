package com.lalabib.latihan.simpleproduct.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lalabib.latihan.simpleproduct.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel  @Inject constructor(productUseCase: ProductUseCase) : ViewModel() {

    val getAllOrder = productUseCase.getAllOrder().asLiveData()
}
package com.lalabib.latihan.simpleproduct.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lalabib.latihan.simpleproduct.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(productUseCase: ProductUseCase) : ViewModel() {

    val getProduct = productUseCase.getAllProduct().asLiveData()

    val getUser = productUseCase.getUser().asLiveData()
}
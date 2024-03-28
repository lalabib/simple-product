package com.lalabib.latihan.simpleproduct.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lalabib.latihan.simpleproduct.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val productUseCase: ProductUseCase) : ViewModel() {

    fun getProductById(id: String) = productUseCase.getProductById(id).asLiveData()
}
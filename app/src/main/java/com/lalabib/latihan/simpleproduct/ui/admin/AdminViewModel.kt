package com.lalabib.latihan.simpleproduct.ui.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lalabib.latihan.simpleproduct.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(private val productUseCase: ProductUseCase) :
    ViewModel() {

    val getAllOrder = productUseCase.getAllOrder().asLiveData()

    val getUser = productUseCase.getUser().asLiveData()

    fun logout() {
        viewModelScope.launch {
            productUseCase.logout()
        }
    }
}
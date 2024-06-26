package com.lalabib.latihan.simpleproduct.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lalabib.latihan.simpleproduct.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val productUseCase: ProductUseCase) :
    ViewModel() {

    val getUser = productUseCase.getUser().asLiveData()

    fun logout() {
        viewModelScope.launch {
            productUseCase.logout()
        }
    }
}
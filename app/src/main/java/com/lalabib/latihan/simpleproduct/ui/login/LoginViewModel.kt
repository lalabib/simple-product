package com.lalabib.latihan.simpleproduct.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lalabib.latihan.simpleproduct.data.local.entity.UserEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.UserPreferenceEntity
import com.lalabib.latihan.simpleproduct.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val productUseCase: ProductUseCase) : ViewModel() {

    val getAllUser = productUseCase.getAllUser().asLiveData()

    fun saveUser(user: UserPreferenceEntity) {
        viewModelScope.launch {
            productUseCase.saveUser(user)
        }
    }

    fun login() {
        viewModelScope.launch {
            productUseCase.login()
        }
    }
}
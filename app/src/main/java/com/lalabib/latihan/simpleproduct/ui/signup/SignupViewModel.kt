package com.lalabib.latihan.simpleproduct.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lalabib.latihan.simpleproduct.data.local.entity.UserEntity
import com.lalabib.latihan.simpleproduct.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val productUseCase: ProductUseCase) :
    ViewModel() {

    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            productUseCase.insertUser(user)
        }
    }
}
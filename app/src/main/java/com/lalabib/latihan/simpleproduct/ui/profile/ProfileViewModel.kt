package com.lalabib.latihan.simpleproduct.ui.profile

import androidx.lifecycle.ViewModel
import com.lalabib.latihan.simpleproduct.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(productUseCase: ProductUseCase) : ViewModel() {


}
package com.lalabib.latihan.simpleproduct.domain.usecase

import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {

    fun getAllProduct(): Flow<List<ProductEntity>>
}
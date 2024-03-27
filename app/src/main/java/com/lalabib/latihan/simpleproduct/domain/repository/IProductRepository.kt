package com.lalabib.latihan.simpleproduct.domain.repository

import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface IProductRepository {

    fun getAllProduct(): Flow<List<ProductEntity>>
}
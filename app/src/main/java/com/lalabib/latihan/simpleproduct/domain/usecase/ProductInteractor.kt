package com.lalabib.latihan.simpleproduct.domain.usecase

import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import com.lalabib.latihan.simpleproduct.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductInteractor @Inject constructor(private val productRepository: IProductRepository) :
    ProductUseCase {

    override fun getAllProduct(): Flow<List<ProductEntity>> = productRepository.getAllProduct()

    override fun getProductById(id: String): Flow<ProductEntity> =
        productRepository.getProductById(id)

}
package com.lalabib.latihan.simpleproduct.data

import com.lalabib.latihan.simpleproduct.data.local.LocalDataSource
import com.lalabib.latihan.simpleproduct.data.local.entity.OrderEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import com.lalabib.latihan.simpleproduct.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : IProductRepository {

    override fun getAllProduct(): Flow<List<ProductEntity>> = localDataSource.getAllProduct()

    override fun getProductById(id: String): Flow<ProductEntity> =
        localDataSource.getProductById(id)

    override suspend fun insertOrder(order: OrderEntity) = localDataSource.insertOrder(order)

    override fun getAllOrder(): Flow<List<OrderEntity>> = localDataSource.getAllOrder()

}
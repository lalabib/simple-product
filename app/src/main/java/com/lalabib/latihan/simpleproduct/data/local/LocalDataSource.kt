package com.lalabib.latihan.simpleproduct.data.local

import com.lalabib.latihan.simpleproduct.data.local.entity.OrderEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import com.lalabib.latihan.simpleproduct.data.local.room.ProductDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val productDao: ProductDao) {

    fun getAllProduct(): Flow<List<ProductEntity>> = productDao.getAllProduct()

    suspend fun insertAllProduct(product: ProductEntity) = productDao.insertAllProduct(product)

    fun getProductById(id: String): Flow<ProductEntity> = productDao.getProductById(id)

    suspend fun insertOrder(order: OrderEntity) = productDao.insertOrder(order)

    fun getAllOrder(): Flow<List<OrderEntity>> = productDao.getAllOrder()
}
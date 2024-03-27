package com.lalabib.latihan.simpleproduct.data.local

import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import com.lalabib.latihan.simpleproduct.data.local.room.ProductDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val productDao: ProductDao) {

    fun getAllProduct(): Flow<List<ProductEntity>> = productDao.getAllProduct()

    fun insertAllProduct(product: ProductEntity) = productDao.insertAllProduct(product)
}
package com.lalabib.latihan.simpleproduct.domain.repository

import com.lalabib.latihan.simpleproduct.data.local.entity.OrderEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.UserEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.UserPreferenceEntity
import kotlinx.coroutines.flow.Flow

interface IProductRepository {

    fun getAllProduct(): Flow<List<ProductEntity>>

    fun getProductById(id: String): Flow<ProductEntity>

    suspend fun insertOrder(order: OrderEntity)

    fun getAllOrder(): Flow<List<OrderEntity>>

    suspend fun insertUser(user: UserEntity)

    fun getAllUser(): Flow<List<UserEntity>>

    fun getUser(): Flow<UserPreferenceEntity>

    suspend fun saveUser(user: UserPreferenceEntity)

    suspend fun login()

    suspend fun logout()
}
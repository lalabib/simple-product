package com.lalabib.latihan.simpleproduct.data

import com.lalabib.latihan.simpleproduct.data.local.LocalDataSource
import com.lalabib.latihan.simpleproduct.data.local.UserPreference
import com.lalabib.latihan.simpleproduct.data.local.entity.OrderEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.UserEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.UserPreferenceEntity
import com.lalabib.latihan.simpleproduct.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val pref: UserPreference
) : IProductRepository {

    override fun getAllProduct(): Flow<List<ProductEntity>> = localDataSource.getAllProduct()

    override fun getProductById(id: String): Flow<ProductEntity> =
        localDataSource.getProductById(id)

    override suspend fun insertOrder(order: OrderEntity) = localDataSource.insertOrder(order)

    override fun getAllOrder(): Flow<List<OrderEntity>> = localDataSource.getAllOrder()

    override suspend fun insertUser(user: UserEntity) = localDataSource.insertUser(user)

    override fun getAllUser(): Flow<List<UserEntity>> = localDataSource.getAllUser()

    override fun getUser(): Flow<UserPreferenceEntity> = pref.getUser()

    override suspend fun saveUser(user: UserPreferenceEntity) = pref.saveUser(user)

    override suspend fun login() = pref.login()

    override suspend fun logout() = pref.logout()
}
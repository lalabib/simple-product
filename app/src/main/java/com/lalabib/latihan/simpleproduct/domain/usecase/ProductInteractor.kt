package com.lalabib.latihan.simpleproduct.domain.usecase

import com.lalabib.latihan.simpleproduct.data.local.entity.OrderEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.UserEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.UserPreferenceEntity
import com.lalabib.latihan.simpleproduct.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductInteractor @Inject constructor(private val productRepository: IProductRepository) :
    ProductUseCase {

    override fun getAllProduct(): Flow<List<ProductEntity>> = productRepository.getAllProduct()

    override fun getProductById(id: String): Flow<ProductEntity> =
        productRepository.getProductById(id)

    override suspend fun insertOrder(order: OrderEntity) = productRepository.insertOrder(order)

    override fun getAllOrder(): Flow<List<OrderEntity>> = productRepository.getAllOrder()
    override suspend fun insertUser(user: UserEntity) = productRepository.insertUser(user)

    override fun getAllUser(): Flow<List<UserEntity>> = productRepository.getAllUser()

    override fun getUser(): Flow<UserPreferenceEntity> = productRepository.getUser()

    override suspend fun saveUser(user: UserPreferenceEntity) = productRepository.saveUser(user)

    override suspend fun login() = productRepository.login()

    override suspend fun logout() = productRepository.logout()
}
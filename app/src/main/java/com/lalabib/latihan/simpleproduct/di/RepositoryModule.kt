package com.lalabib.latihan.simpleproduct.di

import com.lalabib.latihan.simpleproduct.data.ProductRepository
import com.lalabib.latihan.simpleproduct.domain.repository.IProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DatabaseModule::class, DataStoreModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(productRepository: ProductRepository) : IProductRepository
}
package com.lalabib.latihan.simpleproduct.di

import com.lalabib.latihan.simpleproduct.data.ProductRepository
import com.lalabib.latihan.simpleproduct.domain.usecase.ProductInteractor
import com.lalabib.latihan.simpleproduct.domain.usecase.ProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductUseCase(productRepository: ProductRepository): ProductUseCase =
        ProductInteractor(productRepository)
}
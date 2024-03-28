package com.lalabib.latihan.simpleproduct.di

import android.content.Context
import androidx.room.Room
import com.lalabib.latihan.simpleproduct.data.local.room.ProductDao
import com.lalabib.latihan.simpleproduct.data.local.room.ProductDatabase
import com.lalabib.latihan.simpleproduct.utils.StartingProduct
import com.lalabib.latihan.simpleproduct.utils.StartingUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        productProvide: Provider<ProductDao>,
        userProvider: Provider<ProductDao>
    ): ProductDatabase {
        return Room.databaseBuilder(context, ProductDatabase::class.java, "Product.db")
            .addCallback(StartingProduct(context, productProvide))
            .addCallback(StartingUser(context, userProvider))
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProductDao(database: ProductDatabase): ProductDao = database.productDao()
}
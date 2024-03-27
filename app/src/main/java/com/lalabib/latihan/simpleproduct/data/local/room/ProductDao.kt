package com.lalabib.latihan.simpleproduct.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProduct(product: ProductEntity)

    @Query("Select * From product_tb")
    fun getAllProduct(): Flow<List<ProductEntity>>
}
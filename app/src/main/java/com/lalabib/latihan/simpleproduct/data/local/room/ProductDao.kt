package com.lalabib.latihan.simpleproduct.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lalabib.latihan.simpleproduct.data.local.entity.OrderEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProduct(vararg product: ProductEntity)

    @Query("Select * From product_tb")
    fun getAllProduct(): Flow<List<ProductEntity>>

    @Query("Select * from product_tb where id = :id")
    fun getProductById(id: String): Flow<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrder(order: OrderEntity)

    @Query("Select * From order_tb")
    fun getAllOrder(): Flow<List<OrderEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    @Query("Select * From user_tb")
    fun getAllUser(): Flow<List<UserEntity>>
}
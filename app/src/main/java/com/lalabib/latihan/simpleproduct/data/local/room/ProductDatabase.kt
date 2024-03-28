package com.lalabib.latihan.simpleproduct.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lalabib.latihan.simpleproduct.data.local.entity.OrderEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity
import com.lalabib.latihan.simpleproduct.data.local.entity.UserEntity

@Database(
    entities = [ProductEntity::class, OrderEntity::class, UserEntity::class],
    version = 3,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}
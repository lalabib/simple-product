package com.lalabib.latihan.simpleproduct.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lalabib.latihan.simpleproduct.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao
}
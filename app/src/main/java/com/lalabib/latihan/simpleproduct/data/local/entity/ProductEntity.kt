package com.lalabib.latihan.simpleproduct.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_tb")
data class ProductEntity (
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val image: String,
    val author: String,
    val publicationYear: String,
    val publisher: String,
)
package com.lalabib.latihan.simpleproduct.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_tb")
data class OrderEntity (
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val image: String,
    val author: String,
    val publicationYear: String,
    val publisher: String,
    val note: String,
    val quantity: Int,
    val sumPrice: Int
)
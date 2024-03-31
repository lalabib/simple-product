package com.lalabib.latihan.simpleproduct.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tb")
data class UserEntity (
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val role: String,
)

data class UserPreferenceEntity(
    val name: String,
    val email: String,
    val password: String,
    val role: String,
    val isLogin: Boolean
)
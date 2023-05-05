package com.example.astronautsapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "astronauts_table")
data class Astronaut(
    @PrimaryKey val id: Int,
    val age: Int,
    val name: String,
    val profileImg: String,
)

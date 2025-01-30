package com.example.crudapp.models.update

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UpdateUserResponse(
    val name: String,
    val job: String,
    val updatedAt: String
)

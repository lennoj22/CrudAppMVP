package com.example.crudapp.models.add

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CreateUserResponse(
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("job")
    var job  : String? = null,
    @SerializedName("id")
    var id : String? = null,
    @SerializedName("createdAt")
    var createdAt : String? = null
) : Serializable

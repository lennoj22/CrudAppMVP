package com.example.crudapp.models.add

import com.google.gson.annotations.SerializedName

data class CreateUser(
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("job")
    var job  : String? = null
)

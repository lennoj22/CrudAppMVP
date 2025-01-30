package com.example.crudapp.models.update

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UpdateUser(
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("job")
    var job  : String? = null
)

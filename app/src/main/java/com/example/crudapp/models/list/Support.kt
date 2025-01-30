package com.example.crudapp.models.list

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Support(
    @SerializedName("url")
    var url : String? = null,
    @SerializedName("text")
    var text : String? = null
) : Serializable

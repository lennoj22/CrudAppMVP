package com.example.crudapp.models.list

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Data(
    @SerializedName("id")
    var id : Int? = null,
    @SerializedName("email")
    var email : String? = null,
    @SerializedName("first_name")
    var firstName : String? = null,
    @SerializedName("last_name")
    var lastName : String? = null,
    @SerializedName("avatar")
    var avatar : String? = null
) : Serializable

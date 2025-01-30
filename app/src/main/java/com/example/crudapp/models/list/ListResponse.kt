package com.example.crudapp.models.list

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListResponse(
    @SerializedName("page")
    var page : Int? = null,
    @SerializedName("per_page")
    var perPage : Int?            = null,
    @SerializedName("total")
    var total : Int? = null,
    @SerializedName("total_pages")
    var totalPages : Int? = null,
    @SerializedName("data")
    var data : List<Data>,
    @SerializedName("support")
    var support : Support? = Support()
) : Serializable

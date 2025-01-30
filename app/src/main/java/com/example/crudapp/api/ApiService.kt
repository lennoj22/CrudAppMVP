package com.example.crudapp.api

import com.example.crudapp.models.add.CreateUser
import com.example.crudapp.models.add.CreateUserResponse
import com.example.crudapp.models.list.ListResponse
import com.example.crudapp.models.update.UpdateUser
import com.example.crudapp.models.update.UpdateUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    //create
    @POST("api/users")
    fun createUser(@Body request: CreateUser): Call<CreateUserResponse>

    //read
    @GET("api/users?page=2")
    fun getUserList(): Call<ListResponse>

    //update
    @PUT("api/users/{id}")
    fun updateUser(@Path("id") userId: String, @Body request: UpdateUser): Call<UpdateUserResponse>

    //delete
    @DELETE("api/users/{id}")
    fun deleteUser(@Path("id") userId: String): Call<ListResponse>
}
package com.example.crudapp.ui.crud

import com.example.crudapp.api.ApiService
import com.example.crudapp.contract.CrudContract
import com.example.crudapp.models.add.CreateUser
import com.example.crudapp.models.add.CreateUserResponse
import com.example.crudapp.models.list.ListResponse
import com.example.crudapp.models.update.UpdateUser
import com.example.crudapp.models.update.UpdateUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrudFormPresenter(private val view : CrudContract.View,
                        private val apiService: ApiService)
                        : CrudContract.Presenter {

    override fun createUser(name: String, job: String) {
        val request = CreateUser(name, job)
        apiService.createUser(request).enqueue(object : Callback<CreateUserResponse> {
            override fun onResponse(call: Call<CreateUserResponse>, response: Response<CreateUserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { view.onCreateSuccess(it) }
                } else {
                    view.showError("Failed to create user")
                }
            }

            override fun onFailure(call: Call<CreateUserResponse>, t: Throwable) {
                view.showError(t.message ?: "Unknown error")
            }
        })
    }

    override fun updateUser(userId: String, updateUser: UpdateUser) {
        view.showLoading()  // Show loading indicator

        // Make API call to update the user
        apiService.updateUser(userId, updateUser).enqueue(object : Callback<UpdateUserResponse> {
            override fun onResponse(call: Call<UpdateUserResponse>, response: Response<UpdateUserResponse>) {
                view.hideLoading()  // Hide loading indicator

                if (response.isSuccessful) {
                    response.body()?.let {
                        view.onUpdateSuccess(it)  // Call view method if user is updated successfully
                    }
                } else {
                    view.showError("Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                view.hideLoading()  // Hide loading indicator
                view.showError("Failure: ${t.message}")
            }
        })
    }

    override fun deleteUser(userId: String) {
        view.showLoading()  // Show loading indicator

        // Make API call to delete the user
        apiService.deleteUser(userId).enqueue(object : Callback<ListResponse> {
            override fun onResponse(
                call: Call<ListResponse>,
                response: Response<ListResponse>
            ) {
                view.hideLoading()  // Hide loading indicator

                if (response.isSuccessful) {
                    view.onUserDeleted()
                } else {
                    view.showError("Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                view.hideLoading()  // Hide loading indicator
                view.showError("Failure: ${t.message}")
            }
        })
    }
}
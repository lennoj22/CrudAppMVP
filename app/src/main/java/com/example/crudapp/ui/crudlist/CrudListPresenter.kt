package com.example.crudapp.ui.crudlist

import com.example.crudapp.api.ApiService
import com.example.crudapp.contract.CrudContract
import com.example.crudapp.contract.CrudContractList
import com.example.crudapp.models.list.ListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrudListPresenter (private val view: CrudContractList.View,
                         private val apiService: ApiService)
                        : CrudContractList.Presenter {

    override fun fetchUsers() {
        view.showLoading()  // Show loading indicator

        // Make API call to get the user list
        apiService.getUserList().enqueue(object : Callback<ListResponse> {
            override fun onResponse(
                call: Call<ListResponse>,
                response: Response<ListResponse>
            ) {
                view.hideLoading()  // Hide loading indicator

                if (response.isSuccessful) {
                    response.body()?.let {
                        view.onUserListFetched(response.body()?.data ?: emptyList())
                    }
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
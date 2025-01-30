package com.example.crudapp.contract

import com.example.crudapp.models.list.Data
import com.example.crudapp.models.list.ListResponse

interface CrudContractList {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun onUserListFetched(users: List<Data>)
        fun showError(message: String)
    }

    interface Presenter{
        fun fetchUsers()
    }

}
package com.example.crudapp.contract

import com.example.crudapp.models.add.CreateUserResponse
import com.example.crudapp.models.list.Data
import com.example.crudapp.models.list.ListResponse
import com.example.crudapp.models.update.UpdateUser
import com.example.crudapp.models.update.UpdateUserResponse

interface CrudContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun onCreateSuccess(userResponse: CreateUserResponse)
        fun onUpdateSuccess(updateUserResponse: UpdateUserResponse)
        fun onUserDeleted()
        fun onUserListFetched(users: ListResponse)
        fun showError(message: String)
    }

    interface Presenter {
        fun createUser(name: String, job: String)
        fun updateUser(userId: String, updateUser: UpdateUser)
        fun deleteUser(userId: String)
    }
}
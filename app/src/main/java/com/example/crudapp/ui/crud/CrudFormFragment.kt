package com.example.crudapp.ui.crud

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.crudapp.R
import com.example.crudapp.contract.CrudContract
import com.example.crudapp.databinding.CrudFormFragmentBinding
import com.example.crudapp.models.add.CreateUserResponse
import com.example.crudapp.models.list.ListResponse
import com.example.crudapp.models.update.UpdateUser
import com.example.crudapp.models.update.UpdateUserResponse
import com.example.crudapp.network.RetrofitNetwork
import com.example.crudapp.ui.crudlist.CrudListFragment

class CrudFormFragment : Fragment(), CrudContract.View {

    private lateinit var presenter: CrudFormPresenter
    private var crudFormFragmentBinding : CrudFormFragmentBinding? = null
    private val binding get() = crudFormFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        crudFormFragmentBinding = CrudFormFragmentBinding.inflate(inflater, container, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Use ApiClient to get Retrofit instance
        val apiService = RetrofitNetwork.apiService
        presenter = CrudFormPresenter(this, apiService)

        // Handle button clicks with View Binding
        binding!!.btnAddUser.setOnClickListener {
            val name = binding!!.etName.text.toString()
            val job = binding!!.etJob.text.toString()
            if (name.isNotEmpty() && job.isNotEmpty()) {
                presenter.createUser(name, job)  // Call presenter method
            } else {
                Toast.makeText(requireContext(), "Please enter name and job", Toast.LENGTH_SHORT).show()
            }
        }

        binding!!.btnUpdateUser.setOnClickListener {
            val name = binding!!.etName.text.toString()
            val job = binding!!.etJob.text.toString()
            val id = binding!!.etId.text.toString()
            if (name.isNotEmpty() && job.isNotEmpty()) {
                presenter.updateUser(id, UpdateUser(name, job)) // Replace "USER_ID" dynamically
            } else {
                Toast.makeText(requireContext(), "Please enter name and job", Toast.LENGTH_SHORT).show()
            }
        }

        binding!!.btnDeleteUser.setOnClickListener {
            val userId = binding!!.etId.text.toString() // Assuming ID is entered in Name field
            if (userId.isNotEmpty()) {
                presenter.deleteUser(userId)
            } else {
                Toast.makeText(requireContext(), "Please enter user ID to delete", Toast.LENGTH_SHORT).show()
            }
        }

        binding!!.btnNext.setOnClickListener{
            val fragment = CrudListFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun showLoading() {
        binding!!.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding!!.progressBar.visibility = View.GONE
    }

    override fun onCreateSuccess(userResponse: CreateUserResponse) {
        // Hide the progress bar
        hideLoading()

        // Show success message
        Toast.makeText(requireContext(), "id: ${userResponse.id}, " +
                "User Created: ${userResponse.name}, " +
                "Job: ${userResponse.job}, " +
                "Created At: ${userResponse.createdAt}",
                Toast.LENGTH_SHORT).show()

        // Clear input fields after success
        binding!!.etName.text.clear()
        binding!!.etJob.text.clear()
    }

    override fun onUpdateSuccess(updateUserResponse: UpdateUserResponse) {
        // Hide the progress bar
        hideLoading()

        // Show update message
        Toast.makeText(requireContext(), "Job: ${updateUserResponse.job}, " +
                "User Created: ${updateUserResponse.name}, " +
                "Job: ${updateUserResponse.updatedAt}",
                Toast.LENGTH_SHORT).show()

        // Clear input fields after success
        binding!!.etName.text.clear()
        binding!!.etJob.text.clear()
    }

    override fun onUserDeleted() {
        // Hide loading indicator
        hideLoading()

        // Show delete message
        Toast.makeText(requireContext(), "User deleted successfully!", Toast.LENGTH_SHORT).show()

        // Clear the input field (assuming the user enters the ID in etName)
        binding!!.etName.text.clear()
    }

    override fun onUserListFetched(users: ListResponse) {

    }

    override fun showError(message: String) {
        // Hide loading indicator
        hideLoading()

        // Show delete message
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        crudFormFragmentBinding = null  // Prevent memory leaks
    }
}
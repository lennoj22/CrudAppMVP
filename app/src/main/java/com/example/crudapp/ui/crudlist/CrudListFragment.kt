package com.example.crudapp.ui.crudlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudapp.R
import com.example.crudapp.contract.CrudContractList
import com.example.crudapp.databinding.CrudListFragmentBinding
import com.example.crudapp.models.list.Data
import com.example.crudapp.models.list.ListResponse
import com.example.crudapp.network.RetrofitNetwork
import com.example.crudapp.ui.cruddetails.CrudDetailsFragment
import com.example.crudapp.ui.crudlist.adapter.UserAdapter

class CrudListFragment : Fragment(), CrudContractList.View {

    private lateinit var presenter: CrudListPresenter
    private var crudListFragmentBinding : CrudListFragmentBinding? = null
    private val binding get() = crudListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        crudListFragmentBinding = CrudListFragmentBinding.inflate(inflater, container, false)

        binding!!.recyclerView.layoutManager = LinearLayoutManager(context)

        binding!!.swipeRefreshLayout.setOnRefreshListener {
            presenter.fetchUsers()
        }

        presenter = CrudListPresenter(this, RetrofitNetwork.apiService)
        presenter.fetchUsers()

        return binding!!.root
    }

    override fun showLoading() {
        binding!!.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding!!.progressBar.visibility = View.GONE
    }

    override fun onUserListFetched(users: List<Data>) {
        binding!!.recyclerView.adapter = UserAdapter(users) { user ->
            val fragment = CrudDetailsFragment()
            val bundle = Bundle()
            bundle.putSerializable("user", user)
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

        binding!!.swipeRefreshLayout.isRefreshing = false
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        binding!!.swipeRefreshLayout.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        crudListFragmentBinding = null
    }
}
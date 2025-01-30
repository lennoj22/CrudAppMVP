package com.example.crudapp.ui.cruddetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.crudapp.databinding.CrudDetailsFragmentBinding
import com.example.crudapp.models.list.Data

class CrudDetailsFragment : Fragment(){

    private var crudDetailsFragmentBinding : CrudDetailsFragmentBinding? = null
    private val binding get() = crudDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        crudDetailsFragmentBinding = CrudDetailsFragmentBinding.inflate(inflater, container, false)

        val user = arguments?.getSerializable("user") as? Data

        binding!!.nameDetail.text =  "${user!!.firstName} ${user.lastName} ${user.email}"

        binding!!.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        crudDetailsFragmentBinding = null
    }
}
package com.example.ashraf_ehab_mohamed_radi_task.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ashraf_ehab_mohamed_radi_task.R
import com.example.ashraf_ehab_mohamed_radi_task.data.SharedPreferenceManger
import com.example.ashraf_ehab_mohamed_radi_task.data.Status
import com.example.ashraf_ehab_mohamed_radi_task.databinding.FragmentPersonalAccountBinding
import com.example.ashraf_ehab_mohamed_radi_task.databinding.FragmentSignUpBinding
import com.example.ashraf_ehab_mohamed_radi_task.domain.DataBaseBuilder
import com.example.ashraf_ehab_mohamed_radi_task.domain.DataBaseHelper
import com.example.ashraf_ehab_mohamed_radi_task.domain.DatabaseHelperImpl
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.amitshekhar.learn.kotlin.coroutines.utils.ViewModelFactory


class PersonalAccountFragment : Fragment() , OnClickListener {

    private var _binding: FragmentPersonalAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RoomDBViewModel
    private lateinit var dataBaseHelper: DataBaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {

        _binding = FragmentPersonalAccountBinding.inflate(
            inflater
        )
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigating_view)
        navBar.visibility = View.VISIBLE
        SharedPreferenceManger.setup(requireContext())
        dataBaseHelper = DatabaseHelperImpl(DataBaseBuilder.getInstance(requireContext()))
        setupViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserEmailById(SharedPreferenceManger.getUserToken())
        viewModel.getUserById(SharedPreferenceManger.getUserToken())
        userNameObserver()
        userEmailObserver()
        binding.saveBtn.isEnabled = false


        handleClicks()
        setUpAppBar()



    }


    override fun onResume() {
        super.onResume()
        viewModel.getUserEmailById(SharedPreferenceManger.getUserToken())
        viewModel.getUserById(SharedPreferenceManger.getUserToken())

    }

    override fun onDetach() {
        super.onDetach()
        viewModel.getUserEmailById(SharedPreferenceManger.getUserToken())
        viewModel.getUserById(SharedPreferenceManger.getUserToken())
    }


    private fun userNameObserver(){

        viewModel.usernameById.observe(viewLifecycleOwner) {
            when(it.status){
                Status.SUCCESS -> {
                    binding.nameTv.text = it.data
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }

        }

    }
    private fun userEmailObserver(){

        viewModel.usernameById.observe(viewLifecycleOwner) {
            when(it.status){
                Status.SUCCESS -> {
                    binding.emailAdressEt.setText(it.data.toString())
                    binding.emailAdressEt.isEnabled = false
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }

        }

    }

    private fun setUpAppBar() {


        binding.topAppBar.title = getString(R.string.personal_account)

        binding.ibCart.setOnClickListener {

            SharedPreferenceManger.removeUserToken()
            Navigation.findNavController(requireView()).navigate(R.id.action_personalAccountFragment_to_loginFragment)
        }

    }

    private fun handleClicks(){
        binding.btn.setOnClickListener(this)
        binding.saveBtn.setOnClickListener(this)

    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                DatabaseHelperImpl(DataBaseBuilder.getInstance(requireContext()))
            )
        )[RoomDBViewModel::class.java]
    }

    override fun onClick(view: View?) {
        when(view){
            binding.btn -> {
                binding.emailAdressEt.isEnabled = true
                binding.saveBtn.isEnabled = true
            }
            binding.saveBtn -> {
                viewModel.updateEmailById(SharedPreferenceManger.getUserToken().toString(),binding.emailAdressEt.text.toString())
                binding.emailAdressEt.isEnabled = false
                binding.saveBtn.isEnabled = false

            }
        }
    }

}
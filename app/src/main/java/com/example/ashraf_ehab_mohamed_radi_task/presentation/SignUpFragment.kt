package com.example.ashraf_ehab_mohamed_radi_task.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.ashraf_ehab_mohamed_radi_task.R
import com.example.ashraf_ehab_mohamed_radi_task.data.SharedPreferenceManger
import com.example.ashraf_ehab_mohamed_radi_task.data.Status
import com.example.ashraf_ehab_mohamed_radi_task.domain.DatabaseHelperImpl
import com.example.ashraf_ehab_mohamed_radi_task.data.UserEntity
import com.example.ashraf_ehab_mohamed_radi_task.databinding.FragmentSignUpBinding
import com.example.ashraf_ehab_mohamed_radi_task.domain.DataBaseBuilder
import com.example.ashraf_ehab_mohamed_radi_task.domain.DataBaseHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.amitshekhar.learn.kotlin.coroutines.utils.ViewModelFactory


class SignUpFragment : Fragment(), View.OnClickListener {


    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var userEntity: UserEntity
    private lateinit var validationUserEntities: List<UserEntity>
    private lateinit var dataBaseHelper: DataBaseHelper
    private lateinit var viewModel: RoomDBViewModel
    private lateinit var userEmail: String
    private lateinit var userName: String
    private lateinit var userPassword: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(
            inflater
        )
        dataBaseHelper = DatabaseHelperImpl(DataBaseBuilder.getInstance(requireContext()))
        SharedPreferenceManger.setup(requireContext())
        setupViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInTv.setOnClickListener(this)
        binding.registerBtn.setOnClickListener(this)

        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigating_view)
        navBar.visibility = View.GONE

    }

    override fun onResume() {
        super.onResume()
        viewModel.getUser()
    }

    @SuppressLint("ShowToast")
    override fun onClick(view: View?) {
        when (view) {
            binding.signInTv -> {
                Navigation.findNavController(view)
                    .navigate(R.id.action_signUpFragment_to_loginFragment)
            }
            binding.registerBtn -> {

                userName = binding.nameEt.text.toString()
                userPassword = binding.passwordEt.text.toString()
                userEmail = binding.emailEt.text.toString()
                userEntity = UserEntity(email = userEmail, password = userPassword, name = userName)

                viewModel.users.observe(viewLifecycleOwner) {
                    when (it.status) {
                        Status.LOADING -> {
                            binding.registerBtn.isEnabled = false
                            Log.e("TAG101", "loading: ")

                        }
                        Status.SUCCESS -> {
                            binding.registerBtn.isEnabled = true
                            Log.e("TAG101", "success: " + it.data)

                            validationUserEntities = it.data!!

                            if (validationUserEntities.isNullOrEmpty()) {
                                if (userEmail == null){
                                    Toast.makeText(requireActivity(), "Empty", Toast.LENGTH_SHORT)
                                }
                                else{
                                    viewModel.register(userEntity)
                                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT)
                                        .show()
                                    SharedPreferenceManger.saveUserToken(userEntity.password)

                                }


                            }

                            validationUserEntities.forEach {
                                if (it.email.equals(userEmail)) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Already Exist",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return@observe
                                } else {
                                    viewModel.register(userEntity)
                                    SharedPreferenceManger.saveUserToken(userEntity.password)

                                    Log.e("TAG101", "registered: ")
                                    Navigation.findNavController(view)
                                        .navigate(R.id.action_signUpFragment_to_loginFragment)
                                }
                            }

                        }
                        Status.ERROR -> {
                            Log.e("TAG101", "error: ")

                        }
                    }
                }

                Navigation.findNavController(view)
                    .navigate(R.id.action_signUpFragment_to_loginFragment)

            }
        }
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                DatabaseHelperImpl(DataBaseBuilder.getInstance(requireContext()))
            )
        )[RoomDBViewModel::class.java]
    }

}
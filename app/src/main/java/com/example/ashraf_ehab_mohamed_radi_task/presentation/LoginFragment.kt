package com.example.ashraf_ehab_mohamed_radi_task.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.ashraf_ehab_mohamed_radi_task.R
import com.example.ashraf_ehab_mohamed_radi_task.data.SharedPreferenceManger
import com.example.ashraf_ehab_mohamed_radi_task.data.Status
import com.example.ashraf_ehab_mohamed_radi_task.databinding.FragmentLoginBinding
import com.example.ashraf_ehab_mohamed_radi_task.domain.DataBaseBuilder
import com.example.ashraf_ehab_mohamed_radi_task.domain.DataBaseHelper
import com.example.ashraf_ehab_mohamed_radi_task.domain.DatabaseHelperImpl
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.amitshekhar.learn.kotlin.coroutines.utils.ViewModelFactory


class LoginFragment : Fragment() , OnClickListener {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataBaseHelper: DataBaseHelper
    private lateinit var viewModel: RoomDBViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(
            inflater
        )
        dataBaseHelper = DatabaseHelperImpl(DataBaseBuilder.getInstance(context = requireContext()))
        setupViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpTv.setOnClickListener(this)
        binding.signInBtn.setOnClickListener(this)
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigating_view)
        navBar.visibility = View.GONE

        loginObserver()

    }

    override fun onClick(view: View?) {
        when(view){
            binding.signUpTv -> {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment)
            }
            binding.signInBtn -> {
                viewModel.login(binding.userNameEt.text.toString(),binding.passwordEt.text.toString())
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


    private fun loginObserver(){
        viewModel.userLogin.observe(viewLifecycleOwner){
            when(it.status){

                Status.LOADING->{

                }
                Status.SUCCESS->{
                        if (it.data == null){
                            Toast.makeText(requireActivity(),"Login Failed",Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(requireActivity(),"Login Successful",Toast.LENGTH_SHORT).show()
                            SharedPreferenceManger.saveUserToken(binding.passwordEt.text.toString())
                            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_personalAccountFragment)
                        }
                }
                Status.ERROR->{

                }
            }
        }
    }


}
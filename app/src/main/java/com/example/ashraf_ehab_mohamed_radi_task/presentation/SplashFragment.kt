package com.example.ashraf_ehab_mohamed_radi_task.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.ashraf_ehab_mohamed_radi_task.R
import com.example.ashraf_ehab_mohamed_radi_task.data.SharedPreferenceManger
import com.example.ashraf_ehab_mohamed_radi_task.databinding.FragmentLoginBinding
import com.example.ashraf_ehab_mohamed_radi_task.databinding.FragmentSplashBinding
import com.example.ashraf_ehab_mohamed_radi_task.domain.DataBaseBuilder
import com.example.ashraf_ehab_mohamed_radi_task.domain.DatabaseHelperImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {


    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!


    companion object {
        const val DELAY_SPLASH_TIME: Long = 550
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(
            inflater
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSharedPreferenceManger()

        if (!SharedPreferenceManger.getUserToken().isNullOrEmpty())
            Navigation.findNavController(requireView())
                .navigate(R.id.action_splashFragment_to_personalAccountFragment)
        else
            Navigation.findNavController(requireView())
                .navigate(R.id.action_splashFragment_to_loginFragment)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupSharedPreferenceManger() {
        SharedPreferenceManger.setup(requireContext())
    }

}
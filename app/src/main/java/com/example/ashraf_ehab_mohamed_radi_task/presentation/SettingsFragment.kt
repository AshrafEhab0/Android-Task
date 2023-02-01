package com.example.ashraf_ehab_mohamed_radi_task.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.ashraf_ehab_mohamed_radi_task.R
import com.example.ashraf_ehab_mohamed_radi_task.data.SharedPreferenceManger
import com.example.ashraf_ehab_mohamed_radi_task.databinding.FragmentPersonalAccountBinding
import com.example.ashraf_ehab_mohamed_radi_task.databinding.FragmentSettingsBinding
import com.example.ashraf_ehab_mohamed_radi_task.domain.DataBaseBuilder
import com.example.ashraf_ehab_mohamed_radi_task.domain.DataBaseHelper
import com.example.ashraf_ehab_mohamed_radi_task.domain.DatabaseHelperImpl
import me.amitshekhar.learn.kotlin.coroutines.utils.ViewModelFactory


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RoomDBViewModel
    private lateinit var dataBaseHelper: DataBaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {

        _binding = FragmentSettingsBinding.inflate(
            inflater
        )
        SharedPreferenceManger.setup(requireContext())
        dataBaseHelper = DatabaseHelperImpl(DataBaseBuilder.getInstance(requireContext()))
        setupViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAppBar()

    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                DatabaseHelperImpl(DataBaseBuilder.getInstance(requireContext()))
            )
        )[RoomDBViewModel::class.java]
    }



    private fun setUpAppBar() {

        binding.topAppBar.title = getString(R.string.settings)

        binding.ibCart.setOnClickListener {
            SharedPreferenceManger.removeUserToken()
            Navigation.findNavController(requireView()).navigate(R.id.action_settingsFragment_to_loginFragment)


        }

    }


}
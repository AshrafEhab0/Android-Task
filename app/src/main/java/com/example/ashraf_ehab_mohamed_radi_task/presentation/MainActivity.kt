package com.example.ashraf_ehab_mohamed_radi_task.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ashraf_ehab_mohamed_radi_task.R
import com.example.ashraf_ehab_mohamed_radi_task.databinding.ActivityMainBinding
import com.example.ashraf_ehab_mohamed_radi_task.domain.OnSelectModel
import com.example.ashraf_ehab_mohamed_radi_task.domain.replaceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNav()
    }

    private fun initBottomNav() {
        val firstFragment=PersonalAccountFragment()
        val secondFragment=SettingsFragment()

        binding.bottomNavigatingView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.personal->{setCurrentFragment(firstFragment)


                }
                R.id.settings->{setCurrentFragment(secondFragment)

                }

            }
            true
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,fragment)
            commit()
        }

}
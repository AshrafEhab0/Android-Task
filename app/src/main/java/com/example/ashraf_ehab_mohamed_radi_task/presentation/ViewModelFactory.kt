package me.amitshekhar.learn.kotlin.coroutines.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ashraf_ehab_mohamed_radi_task.domain.DataBaseHelper
import com.example.ashraf_ehab_mohamed_radi_task.presentation.RoomDBViewModel

class ViewModelFactory( private val dbHelper: DataBaseHelper) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(dbHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}
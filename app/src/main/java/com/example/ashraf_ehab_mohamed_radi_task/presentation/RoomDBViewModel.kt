package com.example.ashraf_ehab_mohamed_radi_task.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ashraf_ehab_mohamed_radi_task.data.Resource
import com.example.ashraf_ehab_mohamed_radi_task.data.Status
import com.example.ashraf_ehab_mohamed_radi_task.data.UserEntity
import com.example.ashraf_ehab_mohamed_radi_task.domain.DataBaseHelper
import kotlinx.coroutines.launch

class RoomDBViewModel( private val dbHelper: DataBaseHelper) : ViewModel() {

    private val _users =
        MutableLiveData<Resource<List<UserEntity>>>(Resource(Status.LOADING,null,null))
    val users: LiveData<Resource<List<UserEntity>>> get() = _users

    private val _usernameById =
        MutableLiveData<Resource<String>>(Resource(Status.LOADING,null,null))
    val usernameById: LiveData<Resource<String>> get() = _usernameById

    private val _userEmailById =
        MutableLiveData<Resource<String>>(Resource(Status.LOADING,null,null))
    val userEmailById: LiveData<Resource<String>> get() = _userEmailById

    private val _userLogin =
        MutableLiveData<Resource<UserEntity>>(Resource(Status.LOADING,null,null))
    val userLogin: LiveData<Resource<UserEntity>> get() = _userLogin
//
//
    @SuppressLint("SuspiciousIndentation")
     fun getUser(){
        viewModelScope.launch {
            _users.postValue(Resource.loading())
            try {
                val userResponse = dbHelper.getAll()
                    _users.postValue(Resource(Status.SUCCESS, data = userResponse,null))
            } catch (e: Exception) {
                _users.postValue(Resource.error("Something Went Wrong"))
            }
        }
    }

    fun getUserById(id :String?){
        viewModelScope.launch {
            _usernameById.postValue(Resource.loading())
            try {
                val userResponse = dbHelper.getUserInfoById(id)
                _usernameById.postValue(Resource(Status.SUCCESS, data = userResponse,null))
            } catch (e: Exception) {
                _usernameById.postValue(Resource.error("Something Went Wrong"))
            }
        }
    }


    fun login(name : String , password : String){
        viewModelScope.launch {
            _userLogin.postValue(Resource.loading())
            try {
                val userResponse = dbHelper.login(name,password)
                _userLogin.postValue(Resource(Status.SUCCESS, data = userResponse,null))
            } catch (e: Exception) {
                _userLogin.postValue(Resource.error("Something Went Wrong"))
            }
        }
    }


    fun getUserEmailById(id :String?){
        viewModelScope.launch {
            _userEmailById.postValue(Resource.loading())
            try {
                val userResponse = dbHelper.getUserEmailById(id)
                _userEmailById.postValue(Resource(Status.SUCCESS, data = userResponse,null))
            } catch (e: Exception) {
                _userEmailById.postValue(Resource.error("Something Went Wrong"))
            }
        }
    }

     fun register(userEntity: UserEntity){
        viewModelScope.launch {

            dbHelper.registerUSer(userEntity=userEntity)

        }
    }

    private fun getAll(){
        viewModelScope.launch {
            getUser()
        }
    }


     fun updateEmailById(id: String, email:String){
        viewModelScope.launch {
            dbHelper.updateUserEmailById(id,email)
        }
    }

    init {
        getAll()
    }


}
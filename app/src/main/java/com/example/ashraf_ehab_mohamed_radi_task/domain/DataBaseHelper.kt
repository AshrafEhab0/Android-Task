package com.example.ashraf_ehab_mohamed_radi_task.domain

import com.example.ashraf_ehab_mohamed_radi_task.data.UserEntity

interface DataBaseHelper {
    suspend  fun registerUSer(userEntity: UserEntity)

    suspend  fun getAll(): List<UserEntity>
    suspend  fun getUserInfoById(id : String?): String
    suspend  fun getUserEmailById(id : String?): String
    suspend fun updateUserEmailById(id : String?,email : String?)
    suspend fun login(name : String? , password : String?) : UserEntity
}
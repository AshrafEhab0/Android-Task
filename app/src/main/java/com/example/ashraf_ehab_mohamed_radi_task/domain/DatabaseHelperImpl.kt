package com.example.ashraf_ehab_mohamed_radi_task.domain

import com.example.ashraf_ehab_mohamed_radi_task.data.UserDataBase
import com.example.ashraf_ehab_mohamed_radi_task.data.UserEntity

class DatabaseHelperImpl(private val userDataBase: UserDataBase) : DataBaseHelper {

    override suspend fun registerUSer(userEntity: UserEntity) = userDataBase.userDao().registerUSer(userEntity)

    override suspend fun getAll() =  userDataBase.userDao().getAll()
    override suspend fun getUserInfoById(id: String?) = userDataBase.userDao().getUserNameById(id)
    override suspend fun getUserEmailById(id: String?): String = userDataBase.userDao().getUserEmailById(id)
    override suspend fun updateUserEmailById(id: String?, email: String?) = userDataBase.userDao().updateUserEmailById(id,email)
    override suspend fun login(name: String?, password: String?) = userDataBase.userDao().login(name,password)


}
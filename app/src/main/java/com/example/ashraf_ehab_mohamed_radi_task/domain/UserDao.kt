package com.example.ashraf_ehab_mohamed_radi_task.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ashraf_ehab_mohamed_radi_task.data.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun registerUSer(userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE email=:email and password=:password")
    suspend fun login(email : String? , password : String?): UserEntity

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<UserEntity>

    @Query("SELECT name FROM user WHERE password=:email")
    suspend fun getUserNameById(email : String?) :String

    @Query("SELECT email FROM user WHERE password=:email")
    suspend fun getUserEmailById(email : String?) :String

    @Query("UPDATE user SET email=:email WHERE password=:password ")
    suspend fun updateUserEmailById(password: String? ,email : String?)

}
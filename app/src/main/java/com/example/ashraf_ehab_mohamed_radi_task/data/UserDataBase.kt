package com.example.ashraf_ehab_mohamed_radi_task.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ashraf_ehab_mohamed_radi_task.domain.UserDao

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDataBase() : RoomDatabase() {

    abstract fun userDao() : UserDao

}
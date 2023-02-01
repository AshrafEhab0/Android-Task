package com.example.ashraf_ehab_mohamed_radi_task.domain

import android.content.Context
import androidx.room.Room
import com.example.ashraf_ehab_mohamed_radi_task.data.UserDataBase

object DataBaseBuilder {

    private var INSTANCE: UserDataBase? = null

    fun getInstance(context: Context): UserDataBase {
        if (INSTANCE == null) {
            synchronized(UserDataBase::class) {
                if (INSTANCE == null) {
                    INSTANCE = buildRoomDB(context)
                }
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            UserDataBase::class.java,
            "task"
        ).build()

}
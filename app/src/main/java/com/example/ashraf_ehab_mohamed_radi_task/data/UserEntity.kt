package com.example.ashraf_ehab_mohamed_radi_task.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity (

    @ColumnInfo(name = "email")
    var email :String?= "",
    @ColumnInfo(name = "password")
    var password : String? = "",
    @ColumnInfo(name = "name")
    var name : String? = ""
    ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
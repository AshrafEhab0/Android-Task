package com.example.ashraf_ehab_mohamed_radi_task.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.ashraf_ehab_mohamed_radi_task.R
import com.example.ashraf_ehab_mohamed_radi_task.presentation.Constants
import com.example.ashraf_ehab_mohamed_radi_task.presentation.Constants.USER_SESSION

object SharedPreferenceManger {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun setup(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            context.getString(R.string.App_Shared),
            Context.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
    }

    fun clear() = sharedPreferences.edit { clear() }

    fun saveUserToken(userToken: String?) {
        editor.putString(Constants.USER_SESSION, userToken)
        editor.apply()
    }

    fun getUserToken(): String? {
        return sharedPreferences.getString(
            Constants.USER_SESSION,
            Constants.Empty_String
        )
    }

    fun removeUserToken() {
        sharedPreferences.edit()
            .remove(USER_SESSION)
            .apply()
    }




}
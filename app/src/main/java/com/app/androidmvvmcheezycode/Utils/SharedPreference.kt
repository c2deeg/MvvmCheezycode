package com.app.androidmvvmcheezycode.Utils

import android.content.Context
import com.app.androidmvvmcheezycode.Utils.Constants.PREFS_TOKEN_FILE
import com.app.androidmvvmcheezycode.Utils.Constants.TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreference @Inject constructor(@ApplicationContext context: Context) {

    private var prefs = context.getSharedPreferences(PREFS_TOKEN_FILE,Context.MODE_PRIVATE)

    fun saveToken(token: String?){
        val editor = prefs.edit()
        editor.putString(TOKEN,token)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(TOKEN,null)
    }
}
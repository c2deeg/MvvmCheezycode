package com.app.androidmvvmcheezycode

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.androidmvvmcheezycode.Utils.NetworkResult
import com.app.androidmvvmcheezycode.models.LoginRequest
import com.app.androidmvvmcheezycode.models.LoginResponse
import com.app.androidmvvmcheezycode.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel  @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    val userResponseLiveData    :LiveData<NetworkResult<LoginResponse>>
    get() = userRepository.userResponseLiveData

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            userRepository.loginUser(loginRequest)
        }
    }


    fun validateCredentials(email:String,password:String):Pair<Boolean,String>{
        var result = Pair(true,"")
        if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
            result = Pair(false,"Please Provide Credentials")
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            result = Pair(false,"Please Enter Valid Email")

        }else if(password.length<=2){
            result = Pair(false,"Password length should be greater than 5")
        }
        return result

    }
}
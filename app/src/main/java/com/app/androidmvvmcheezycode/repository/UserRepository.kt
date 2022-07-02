package com.app.androidmvvmcheezycode.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.androidmvvmcheezycode.Utils.Constants.TAG
import com.app.androidmvvmcheezycode.Utils.NetworkResult
import com.app.androidmvvmcheezycode.Utils.SharedPreference
import com.app.androidmvvmcheezycode.api.UserAPI
import com.app.androidmvvmcheezycode.models.LoginRequest
import com.app.androidmvvmcheezycode.models.LoginResponse
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserAPI) {

    private val _useresponseLiveData = MutableLiveData<NetworkResult<LoginResponse>>()
    val userResponseLiveData:LiveData<NetworkResult<LoginResponse>>
    get() = _useresponseLiveData
    @Inject
    lateinit var sharedPreference: SharedPreference

    suspend fun loginUser(loginRequest: LoginRequest){
        _useresponseLiveData.postValue(NetworkResult.Loading())
        val response = userAPI.login(loginRequest)
        handleResponse(response)
//        Log.d(TAG,response.body().toString())

    }

    private fun handleResponse(response: Response<LoginResponse>) {
        if (response.isSuccessful && response.body() != null) {
            var acesstoken: String? = null
            acesstoken = response.headers()["x-access-token"]
            sharedPreference.saveToken(acesstoken)

            if (acesstoken != null) {
                Log.v(TAG, acesstoken)
            }else{
                Log.v(TAG,"empty")
            }


//            sharedPreference.saveToken(headertoken)



            _useresponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorbody = JSONObject(response.errorBody()!!.charStream().readText())
            _useresponseLiveData.postValue(NetworkResult.Error(errorbody.getString("error")))

        } else {
            _useresponseLiveData.postValue(NetworkResult.Error("something went wrong"))

        }
    }
}
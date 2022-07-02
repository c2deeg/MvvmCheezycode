package com.app.androidmvvmcheezycode.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.androidmvvmcheezycode.AuthViewModel
import com.app.androidmvvmcheezycode.R
import com.app.androidmvvmcheezycode.Utils.NetworkResult
import com.app.androidmvvmcheezycode.Utils.SharedPreference
import com.app.androidmvvmcheezycode.databinding.FragmentLoginBinding
import com.app.androidmvvmcheezycode.models.LoginRequest
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AuthViewModel>()
    @Inject
    lateinit var sharedPreference: SharedPreference



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        if (sharedPreference.getToken()!=null){
            findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
        }else{

        }


        return binding.root
    }
//    binding.tvRegiternow.setOnClickListener{
//        it.findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    binding.btnLogin.setOnClickListener{
            val validationResult = validateUSerInput()
        if (validationResult.first){
            authViewModel.loginUser(getUserRequest())

        }else{
            Toast.makeText(requireContext(),validationResult.second,Toast.LENGTH_SHORT).show()

        }
    }

    bindObservers()



    }

    private fun getUserRequest():LoginRequest{
        val emailAddress = binding.etEmail.text.toString()
        val userPassword = binding.etPassword.text.toString()
        return LoginRequest(emailAddress,userPassword)
    }
    private fun validateUSerInput(): Pair<Boolean, String> {
        val userrequest = getUserRequest()

        return authViewModel.validateCredentials(userrequest.email, userrequest.password)
    }

    private fun bindObservers() {
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = false
            when(it){
                is NetworkResult.Success ->{
//                    sharedPreference.saveToken(it.data?.data?._id)
//                    sharedPreference.getToken()
                    Toast.makeText(requireContext(),sharedPreference.getToken(),Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
                }
                is NetworkResult.Error ->{
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading ->{
                    binding.progress.isVisible = true
                }
            }
        })

    }

}
package com.example.tport.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tport.databinding.FragmentLoginBinding
import com.example.tport.util.AuthStorage
import com.example.tport.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private val viewModel: UserViewModel by viewModel()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authStorage: AuthStorage by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName = binding.name
        val password = binding.password
        val loginButton = binding.login
        val loadingProgressBar = binding.loading
        val upButton = binding.upButton

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE

            lifecycleScope.launch {
                // request server user login with email and password
                val response = async(Dispatchers.IO) { viewModel.login(userName.text.toString(), password.text.toString()) }
                response.await()
                loadingProgressBar.visibility = View.INVISIBLE
                authStorage.authInfo.collect {
                    if (it == null) {
                        Log.d("LoginFragment", "Unauthorized error")
                    } else {
                        Log.d("LoginFragment", "Login Succeeded")
                        val action = LoginFragmentDirections.actionLoginFragmentToSearchFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }

        // navigate up action
        upButton.setOnClickListener {
            this.findNavController().navigateUp()
        }
    }
}
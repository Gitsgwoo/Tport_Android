package com.example.tport.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tport.databinding.FragmentSignUpBinding
import com.example.tport.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignupFragment : Fragment() {

    private val viewModel: UserViewModel by viewModel()
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = binding.name
        val password = binding.password
        val signupButton = binding.signup
        val loadingProgressBar = binding.loading



        signupButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE

            lifecycleScope.launch{
                viewModel.userTags.collect {
                    val response = async(Dispatchers.IO) {
                        viewModel.signup(name.text.toString(), password.text.toString())
                    }
                    response.await()
                    loadingProgressBar.visibility = View.INVISIBLE
                    val action = SignupFragmentDirections.actionSignupFragmentToLoginFragment()
                    findNavController().navigate(action)
                }
            }
        }
    }


}
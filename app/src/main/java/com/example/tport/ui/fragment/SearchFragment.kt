package com.example.tport.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tport.R
import com.example.tport.databinding.FragmentSearchBinding
import com.example.tport.util.AuthStorage
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() =_binding!!
    private val authStorage: AuthStorage by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            authStorage.authInfo.collect {
                val token = it?.accessToken
                val id = it?.user?.id
                Log.d("MyNoteFragment", "Token: $token")
                Log.d("MyNoteFragment", "id: $id")
                if (it == null) {
                    Log.d("MyNoteFragment", "start navigate to login_graph")
                    findNavController().navigate(R.id.action_global_login_graph)
                }
//                findNavController().navigate(R.id.action_global_login_graph)
            }
        }

        val searchButton = binding.buttonSearch

        searchButton.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToPathFindingFragment()
            findNavController().navigate(action)
        }
    }
}
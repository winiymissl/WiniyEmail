package com.example.winiychat.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.winiychat.R
import com.example.winiychat.databinding.FragmentConfigBinding


class ConfigFragment : Fragment() {
    private val binding by lazy {
        FragmentConfigBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ConfigFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
package com.example.winiychat.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.winiychat.databinding.FragmentInBoxBinding
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException


class InBoxFragment : Fragment() {
    private val binding: FragmentInBoxBinding by lazy { FragmentInBoxBinding.inflate(layoutInflater) }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = InBoxFragment()
    }
}
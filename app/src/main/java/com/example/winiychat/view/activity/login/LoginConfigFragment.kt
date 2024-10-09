package com.example.winiychat.view.activity.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
import androidx.fragment.app.Fragment
import androidx.transition.Scene
import androidx.transition.TransitionManager
import com.example.winiychat.databinding.FragmentLoginConfigBinding


class LoginConfigFragment : Fragment() {
    private val binding by lazy {
        FragmentLoginConfigBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding.frameLayoutLoginConfig.post {
            binding.frameLayoutLoginConfig.startAnimation(ScaleAnimation(1f, 1f, 0f, 1f).apply {
                duration = 500
            })
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonConfigNextStep.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.constraintLayoutLoginConfig)
            binding.layoutIncludeLoginConfigLoading.layoutLoginConfigLoading.visibility = View.VISIBLE
        }
    }
}
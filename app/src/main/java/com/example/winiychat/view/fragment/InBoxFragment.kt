package com.example.winiychat.view.fragment

import android.os.Bundle
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter4.BaseQuickAdapter.AnimationType
import com.example.winiychat.databinding.FragmentInBoxBinding
import com.example.winiychat.model.bean.InBoxRecyclerviewBean
import com.example.winiychat.view.adapter.InBoxRecyclerviewAdapter
import com.example.winiychat.R
import com.google.android.material.transition.MaterialSharedAxis

class InBoxFragment : Fragment() {

    private val binding: FragmentInBoxBinding by lazy { FragmentInBoxBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = InBoxRecyclerviewAdapter()
        adapter.submitList(
            listOf(
                InBoxRecyclerviewBean(
                    "winiy",
                    false,
                    "123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123",
                    "hello world",
                    "10.7.2024",
                    "",
                    false
                ), InBoxRecyclerviewBean(
                    "winiy",
                    true,
                    "123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123",
                    "hello world",
                    "10.7.2024",
                    "",
                    false
                ), InBoxRecyclerviewBean(
                    "winiy",
                    true,
                    "123123123123123123123123123123123",
                    "hello world",
                    "10.7.2024",
                    "",
                    false
                ), InBoxRecyclerviewBean(
                    "winiy", true, "123", "hello world", "10.7.2024", "", false
                ), InBoxRecyclerviewBean(
                    "winiy", true, "123", "hello world", "10.7.2024", "", false
                ), InBoxRecyclerviewBean(
                    "winiy", true, "123", "hello world", "10.7.2024", "", false
                ), InBoxRecyclerviewBean(
                    "winiy", true, "123", "hello world", "10.7.2024", "", false
                )
            )
        )

        adapter.apply {
            setItemAnimation(AnimationType.ScaleIn)
            isAnimationFirstOnly = false
            isStateViewEnable = true
            animationEnable = true
            setOnItemClickListener { adapter, view, position ->
                NavHostFragment.findNavController(this@InBoxFragment)
                    .navigate(R.id.emailDetailFragment)
            }
            setOnItemLongClickListener { adapter, view, position ->
                Toast.makeText(activity, "LongClick", Toast.LENGTH_SHORT).show()
                true
            }
        }

        binding.recyclerviewInbox.apply {
            this.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = InBoxFragment()
    }
}
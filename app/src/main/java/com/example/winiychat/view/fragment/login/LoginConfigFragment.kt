package com.example.winiychat.view.fragment.login

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.winiychat.databinding.FragmentLoginConfigBinding
import com.example.winiychat.viewmodel.request.VerifyEmailEvent
import com.example.winiychat.viewmodel.state.LoginEmailViewModel
import com.jeremyliao.liveeventbus.LiveEventBus
import com.example.winiychat.R
import com.example.winiychat.viewmodel.application.GlobalViewModel
import com.tencent.mmkv.MMKV

class LoginConfigFragment : Fragment() {
    private lateinit var loginEmailViewModel: LoginEmailViewModel

    val TAG = "LoginConfigFragment"
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
        binding.layoutIncludeLoginConfigResult.layoutIncludeLoginConfigSuccess.layoutLoginConfigSuccess.post {
            binding.layoutIncludeLoginConfigResult.layoutIncludeLoginConfigSuccess.layoutLoginConfigSuccess.startAnimation(
                ScaleAnimation(
                    1f, 1f, 0f, 1f
                ).apply {
                    duration = 500
                })
        }
        return binding.root
    }

    private var step = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginEmailViewModel = ViewModelProvider(requireActivity())[LoginEmailViewModel::class.java]
        //LiveEventBus 监听邮箱配置是否正确
        LiveEventBus.get<VerifyEmailEvent>(VerifyEmailEvent::class.java)
            .observe(viewLifecycleOwner) { it ->
                loadingGone()
                resultVisible()
                if (it.isSuccess) {
                    successVisible()
                } else {
                    failureVisible()
                }
            }
        //next按钮
        binding.buttonNext.setOnClickListener {
            if (step == 1) {
                val globalViewModel = GlobalViewModel.getInstance(requireActivity().application)
//                globalViewModel.requestConfigEmail()

                loadingVisible()
                loginEmailViewModel.requestEmail(binding.layoutIncludeLoginConfigNormal.textInputView.editableText.toString())
                step++
            } else if (step == 2) {
                val mmkv = MMKV.defaultMMKV()
                mmkv.encode(
                    "email",
                    binding.layoutIncludeLoginConfigNormal.textInputView.editableText.toString()
                )
                //跳转到MainActivity
            }
        }
        binding.layoutIncludeLoginConfigResult.layoutIncludeLoginConfigSuccess.cardViewConfig.setOnClickListener {
            //收发服务器设置
            NavHostFragment.findNavController(this@LoginConfigFragment).navigate(R.id.loginFragment)
        }
        //previous按钮
        binding.buttonPrevious.setOnClickListener {
            if (step == 2) {
                normalVisible()
                step--
            }
        }
        //bottomAppBar 底部键盘
        val activityView = activity?.window?.decorView
        activityView!!.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            activityView.getWindowVisibleDisplayFrame(r)
            val screenHeight = activityView.rootView.height
            // 计算键盘高度
            val keyboardHeight = screenHeight - r.bottom
            // 根据键盘高度调整 BottomAppBar 的位置
            val params = binding.bottomAppBar.layoutParams as CoordinatorLayout.LayoutParams
            params.bottomMargin = keyboardHeight
            binding.bottomAppBar.layoutParams = params
        }
    }

    private fun normalVisible() {
        binding.layoutIncludeLoginConfigResult.fragmentLoginConfigAll.visibility = View.GONE
        binding.layoutIncludeLoginConfigNormal.layoutLoginConfigNormal.visibility = View.VISIBLE
        binding.layoutIncludeLoginConfigNormal.layoutLoginConfigNormal.startAnimation(ScaleAnimation(
            1f, 1f, 0f, 1f
        ).apply {
            duration = 500
        })
    }

    private fun successVisible() {
        binding.layoutIncludeLoginConfigNormal.layoutLoginConfigNormal.visibility = View.GONE
        binding.layoutIncludeLoginConfigResult.layoutIncludeLoginConfigFailure.layoutLoginConfigFailure.visibility =
            View.GONE
        binding.layoutIncludeLoginConfigResult.layoutIncludeLoginConfigSuccess.layoutLoginConfigSuccess.visibility =
            View.VISIBLE
        binding.layoutIncludeLoginConfigResult.layoutIncludeLoginConfigSuccess.layoutLoginConfigSuccess.startAnimation(
            ScaleAnimation(
                1f, 1f, 0f, 1f
            ).apply {
                duration = 500
            })
    }

    private fun loadingGone() {
        binding.layoutIncludeLoginConfigLoading.layoutLoginConfigLoading.visibility = View.GONE
    }

    private fun resultVisible() {
        binding.layoutIncludeLoginConfigLoading.layoutLoginConfigLoading.visibility = View.GONE
        binding.layoutIncludeLoginConfigNormal.layoutLoginConfigNormal.visibility = View.GONE
        binding.layoutIncludeLoginConfigResult.fragmentLoginConfigAll.visibility = View.VISIBLE
    }

    private fun failureVisible() {
        binding.layoutIncludeLoginConfigNormal.layoutLoginConfigNormal.visibility = View.GONE
        binding.layoutIncludeLoginConfigResult.layoutIncludeLoginConfigFailure.layoutLoginConfigFailure.visibility =
            View.GONE
        binding.layoutIncludeLoginConfigResult.layoutIncludeLoginConfigFailure.layoutLoginConfigFailure.visibility =
            View.VISIBLE

    }

    private fun loadingVisible() {
        binding.layoutIncludeLoginConfigNormal.layoutLoginConfigNormal.visibility = View.GONE
        binding.layoutIncludeLoginConfigResult.fragmentLoginConfigAll.visibility = View.GONE
        binding.layoutIncludeLoginConfigLoading.layoutLoginConfigLoading.visibility = View.VISIBLE
    }
}
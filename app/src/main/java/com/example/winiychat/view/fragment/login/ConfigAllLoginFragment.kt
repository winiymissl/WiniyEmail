package com.example.winiychat.view.fragment.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.winiychat.R
import com.example.winiychat.databinding.FragmentConfigAllLoginBinding
import com.example.winiychat.model.LoggedInUserView
import com.example.winiychat.application.GlobalViewModel
import com.example.winiychat.viewmodel.state.LoginViewModel
import com.example.winiychat.viewmodel.state.LoginViewModelFactory

class ConfigAllLoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentConfigAllLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfigAllLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val globalViewModel =
            GlobalViewModel.getInstance(application = requireActivity().application)
        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        globalViewModel.getConfigLiveData().observe(viewLifecycleOwner, Observer { it ->
            binding.MaterialAutoCompleteTextViewProtocol.text =
                Editable.Factory.getInstance().newEditable(it.protocol_receive)
            binding.TextInputEditTextUsername.setText(it.username)
            binding.TextInputEditTextPsswrd.setText(it.password)
            binding.TextInputEditTextServer.setText(it.server)
            binding.TextInputEditTextPort.setText(it.port.toString())
            binding.MaterialSwitchIsSSL.isChecked = it.isSSLorTLS!!
        })
        val items = arrayOf("IMAP", "POP3")
        binding.MaterialAutoCompleteTextViewProtocol.setSimpleItems(items)
        binding.chipBack.setOnClickListener {
            //更新最新的ViewModel
            var temp = GlobalViewModel.UserInfo.EmailConfigInfo()
            temp.username = binding.TextInputEditTextUsername.editableText.toString()
            temp.password = binding.TextInputEditTextPsswrd.editableText.toString()
            temp.server = binding.TextInputEditTextServer.editableText.toString()
            try {
                temp.port = if (binding.TextInputEditTextPort.editableText.toString()
                        .isNullOrBlank()
                ) 0 else binding.TextInputEditTextPort.editableText.toString().toInt()
                temp.isSSLorTLS = binding.MaterialSwitchIsSSL.isChecked
                temp.protocol_receive = binding.MaterialAutoCompleteTextViewProtocol.text.toString()
            } catch (e: Exception) {
                Log.d("asdfasdf", "$e")
            }
            globalViewModel.requestConfigEmail(
                username = temp.username!!,
                password = temp.password!!,
                server = temp.server!!,
                port = temp.port!!,
                protocol_receive = temp.protocol_receive!!,
                isSSLorTLS = temp.isSSLorTLS!!
            )
            Log.d("asdfasdf", "$temp")
            NavHostFragment.findNavController(this@ConfigAllLoginFragment).popBackStack()
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
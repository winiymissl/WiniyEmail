package com.example.winiychat.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.winiychat.R
import com.example.winiychat.databinding.ActivitySplashBinding
import com.example.winiychat.view.activity.login.LoginActivity
import com.example.winiychat.view.activity.main.MainActivity
import com.tencent.mmkv.MMKV
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class SplashActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    fun sendEmail(username: String, password: String, to: String, subject: String, body: String) {
        try {
            val props = Properties()

            props["mail.smtp.host"] = "smtp.163.com" // 替换为您使用的邮件服务器
            props["mail.smtp.port"] = "25"
            props["mail.smtp.auth"] = "true"
            props["mail.smtp.ssl.enable"] = "false"
//            props["mail.smtp.starttls.enable"] = "true"

            val session = Session.getInstance(props, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(username, password)
                }
            })
            Log.d("hsijiasdfasdfasdf", "邮件发送ing")

            val message = MimeMessage(session)
            message.setFrom(InternetAddress(username))
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))
            message.setSubject(subject)
            message.setText(body)

            Transport.send(message)

            Log.d("hsijiasdfasdfasdf", "邮件已发送")
        } catch (e: Exception) {
            Log.d("hsijiasdfasdfasdf", e.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Handler(Looper.getMainLooper()).postDelayed({
            when (isToLogin()) {
                true -> startActivity(Intent(this, MainActivity::class.java))
                false -> startActivity(Intent(this, LoginActivity::class.java))
            }
        }, 3000)
    }

    private fun isToLogin(): Boolean {
        return MMKV.defaultMMKV().contains("config")
    }
}
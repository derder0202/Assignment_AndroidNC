package com.example.assignment_androidnc.Social

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_androidnc.databinding.ActivitySocialBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class SocialActivity : AppCompatActivity() {
    lateinit var binding:ActivitySocialBinding
    lateinit var callbackManager: CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callbackManager = CallbackManager.Factory.create()
        val loginBtn = binding.loginBtn
        loginBtn.registerCallback(callbackManager,object : FacebookCallback<LoginResult>{
            override fun onCancel() {
                Toast.makeText(this@SocialActivity,"cancel",Toast.LENGTH_SHORT).show()
            }
            override fun onError(error: FacebookException?) {
                Toast.makeText(this@SocialActivity,"loi",Toast.LENGTH_SHORT).show()
            }
            override fun onSuccess(result: LoginResult?) {
                Toast.makeText(this@SocialActivity,"thanh cong",Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

}
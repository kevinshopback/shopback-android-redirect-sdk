package com.shopback.sample

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shopback.redirectsdk.Domain
import com.shopback.redirectsdk.InitListener
import com.shopback.redirectsdk.RedirectionListener
import com.shopback.redirectsdk.RedirectSDK

class MainActivity : AppCompatActivity() {

    private lateinit var btnRedirection: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupClickListeners()
        initRedirectSDK()
    }

    private fun initViews() {
        btnRedirection = findViewById(R.id.btnRedirection)
    }

    private fun initRedirectSDK() {
        RedirectSDK.init(
            context = this,
            domain = Domain.AU,
            authorization = "jwt_token_here",
            userId = "user_id_here",
            listener = object : InitListener {
                override fun onInitSuccess() {
                    Toast.makeText(this@MainActivity, "RedirectSDK initialized successfully", Toast.LENGTH_SHORT).show()
                    enableButton(true)
                }

                override fun onInitError(throwable: Throwable) {
                    Toast.makeText(this@MainActivity, "RedirectSDK init failed: ${throwable.message}", Toast.LENGTH_LONG).show()
                    enableButton(false)
                }
            }
        )
    }

    private fun setupClickListeners() {
        btnRedirection.setOnClickListener {
            startRedirection()
        }

        // Initially disable button until SDK is initialized
        enableButton(false)
    }

    private fun startRedirection() {
        RedirectSDK.startRedirection(
            context = this,
            redirectId = "redirect_id_here",
            listener = object : RedirectionListener {
                override fun onRedirectSuccess() {
                    Toast.makeText(this@MainActivity, "Redirect successful", Toast.LENGTH_SHORT).show()
                }

                override fun onRedirectError(throwable: Throwable) {
                    Toast.makeText(this@MainActivity, "Redirect failed: ${throwable.message}", Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    private fun enableButton(enabled: Boolean) {
        btnRedirection.isEnabled = enabled
    }
}
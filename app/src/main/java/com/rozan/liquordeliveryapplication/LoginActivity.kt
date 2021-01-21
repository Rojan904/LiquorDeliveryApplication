package com.rozan.liquordeliveryapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    private lateinit var tvSignUp:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvSignUp=findViewById(R.id.tvSignUp)
        tvSignUp.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }
}
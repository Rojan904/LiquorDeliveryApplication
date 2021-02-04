package com.rozan.liquordeliveryapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.rozan.liquordeliveryapplication.db.AilaDB
import com.rozan.liquordeliveryapplication.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var etUsername:EditText
    private lateinit var etPassword:EditText
    private lateinit var tvSignUp:TextView
    private lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername=findViewById(R.id.etUsername)
        etPassword=findViewById(R.id.etPassword)
        tvSignUp=findViewById(R.id.tvSignUp)
        btnLogin=findViewById(R.id.btnLogin)
        tvSignUp.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }
        btnLogin.setOnClickListener{
            login()
        }
    }

    private fun login() {
        if (checkEmpty()) {


            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            var user: User? = null
            CoroutineScope(Dispatchers.IO).launch {
                user = AilaDB
                    .getInstance(this@LoginActivity)
                    .getUserDAO()
                    .checkUser(username, password)
                if (user == null) {
                    withContext(Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Invalid username or password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    saveSharedPref()
                    startActivity(Intent(this@LoginActivity, AilaActivity::class.java))

                }
            }
        }
    }

    private fun saveSharedPref() {
        val username=etUsername.text.toString()
        val password=etPassword.text.toString()
        val sharedPref=getSharedPreferences("MyPref", MODE_PRIVATE)  //shared preference banako
        val editor=sharedPref.edit()

        editor.putString("username",username)
        editor.putString("password",password)

        editor.apply()

    }

    private fun checkEmpty():Boolean{
       var flag=true
       when{
           TextUtils.isEmpty(etUsername.text) -> {
               etUsername.error = "Enter your username"
               etUsername.requestFocus()
               flag = false
           }

           TextUtils.isEmpty(etPassword.text) -> {
               etPassword.error = "Enter your password"
               etPassword.requestFocus()
               flag = false
           }
       }
        return flag
    }
}
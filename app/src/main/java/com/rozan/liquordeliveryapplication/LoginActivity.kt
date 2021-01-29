package com.rozan.liquordeliveryapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.rozan.liquordeliveryapplication.db.CustomerDB
import com.rozan.liquordeliveryapplication.entity.Customer
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
        val username=etUsername.text.toString()
        val password=etPassword.text.toString()
        var customer: Customer?=null
        CoroutineScope(Dispatchers.IO).launch {
            customer=CustomerDB
                    .getInstance(this@LoginActivity)
                    .getCustomerDAO()
                    .checkCustomer(username,password)
            if (customer==null){
                withContext(Main){
                    Toast.makeText(this@LoginActivity, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                startActivity(Intent(this@LoginActivity,AilaActivity::class.java))
            }
        }
    }
}
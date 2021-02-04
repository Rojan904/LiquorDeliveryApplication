package com.rozan.liquordeliveryapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //Background thread, usually for showing while server checks user details.
        CoroutineScope(Dispatchers.IO).launch { //coroutine scope is a light weight thread.
            delay(1000)


            checkLogin()
            finish()
        }
    }

    private fun checkLogin() {
    val sharedPref=getSharedPreferences("MyPref", MODE_PRIVATE)
    val username=sharedPref.getString("username","")
    val password=sharedPref.getString("password","")
        if (username != null && !username.equals("")) {
            startActivity(Intent(this@SplashScreenActivity,AilaActivity::class.java))
    }
        else{
            startActivity(Intent(this@SplashScreenActivity,LoginActivity::class.java))
        }
    }
}
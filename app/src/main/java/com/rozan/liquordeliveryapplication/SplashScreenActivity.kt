package com.rozan.liquordeliveryapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.github.ybq.android.spinkit.style.Wave
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        //Background thread, usually for showing while server checks user details.
        CoroutineScope(Dispatchers.IO).launch { //coroutine scope is a light weight thread.
            delay(3000)
            runOnUiThread(kotlinx.coroutines.Runnable {

                progressBar = findViewById(R.id.spin_kit)
                val doubleBounce: Sprite = DoubleBounce()
                progressBar.indeterminateDrawable = doubleBounce
//                finish()

            })
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
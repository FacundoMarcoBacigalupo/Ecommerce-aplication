package com.facundo.ecommerce
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.facundo.ecommerce.Seller.MainActivitySeller

class SplashScreenActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_splash_screen)
      viewWelcome()
   }

   private fun viewWelcome() {
      object : CountDownTimer(3000,1000){
         override fun onTick(p0: Long) {

         }

         override fun onFinish() {
            startActivity(Intent(applicationContext, MainActivitySeller::class.java))
            finishAffinity()
         }
      }.start()
   }

}
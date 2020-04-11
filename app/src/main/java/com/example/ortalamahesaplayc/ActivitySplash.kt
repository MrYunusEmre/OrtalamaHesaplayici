package com.example.ortalamahesaplayc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var asagidanGelenButon = AnimationUtils.loadAnimation(this,R.anim.asagidangelenbuton)
        var yukaridanGelenBalon = AnimationUtils.loadAnimation(this,R.anim.yukaridangelenbalon)
        var asagıyaGeriGidenButon = AnimationUtils.loadAnimation(this,R.anim.asagigidenbuton)
        var yukarıdonenbalon = AnimationUtils.loadAnimation(this,R.anim.yukarigidenbalon)

        btnOrtHesapla.animation = asagidanGelenButon
        balon.animation = yukaridanGelenBalon

        btnOrtHesapla.setOnClickListener{

            btnOrtHesapla.startAnimation(asagıyaGeriGidenButon)
            balon.startAnimation(yukarıdonenbalon)

            object : CountDownTimer(1000,1000){
                override fun onFinish() {
                    var intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onTick(millisUntilFinished: Long) {

                }

            }.start()




        }
    }
}

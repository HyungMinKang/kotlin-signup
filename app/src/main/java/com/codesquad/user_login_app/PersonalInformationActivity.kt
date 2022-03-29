package com.codesquad.user_login_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PersonalInformationActivity : AppCompatActivity() {

    private lateinit var nextBtn:Button
    private lateinit var prevBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_info)
        initView()
        setPrevButton()
    }

    private fun initView(){
        nextBtn= findViewById(R.id.btn_next)
        prevBtn= findViewById(R.id.btn_prev)
    }

    private fun setPrevButton(){
        prevBtn.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.slide_left_enter)
        }
    }
}
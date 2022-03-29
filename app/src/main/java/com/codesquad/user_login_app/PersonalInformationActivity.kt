package com.codesquad.user_login_app

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class PersonalInformationActivity : AppCompatActivity() {

    private lateinit var nextBtn: Button
    private lateinit var prevBtn: Button
    private lateinit var birthdate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_info)
        initView()
        setPrevButton()
        setBirthDateInput()
    }

    private fun initView() {
        nextBtn = findViewById(R.id.btn_next)
        prevBtn = findViewById(R.id.btn_prev)
        birthdate = findViewById(R.id.edit_text_birthdate)
    }

    private fun setPrevButton() {
        prevBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.slide_left_enter)
        }
    }

    private fun setBirthDateInput() {
        birthdate.setOnClickListener {
            showDateDialog()
        }
    }

    private fun showDateDialog() {
        val minDate = Calendar.getInstance()
        val maxDate = Calendar.getInstance()
        maxDate.set(2009,11,31)
        minDate.set(1923,0,1)
        val maxYear = maxDate.get(Calendar.YEAR)
        val maxMonth = maxDate.get(Calendar.MONTH)
        val maxDay = maxDate.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(this, birthDateSetListener, maxYear, maxMonth, maxDay)
        dialog.datePicker.minDate= minDate.timeInMillis
        dialog.datePicker.maxDate= maxDate.timeInMillis
        dialog.show()
    }

    private val birthDateSetListener = DatePickerDialog.OnDateSetListener() { view, year, month, dayOfMonth ->
            val dateString = "${year}년 ${month + 1}월 ${dayOfMonth}일"
            birthdate.setText(dateString)
        }
}



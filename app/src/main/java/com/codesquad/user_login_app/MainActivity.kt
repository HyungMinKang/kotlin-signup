package com.codesquad.user_login_app


import android.content.Intent
import android.icu.number.Scale.none
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {
    private lateinit var id: EditText
    private lateinit var password: EditText
    private lateinit var passwordConfirm: EditText
    private lateinit var name: EditText
    private lateinit var idTextLayout: TextInputLayout
    private lateinit var passwordTextLayout: TextInputLayout
    private lateinit var passwordConfirmTextLayout: TextInputLayout
    private lateinit var nameTextLayout:TextInputLayout
    private lateinit var nextBtn:Button
    private var idFlag=false
    private var passwordFlag= false
    private var passwordConfirmFlag= false
    private var nameFlag= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        checkValidation()
        setNextBtnListener()
    }

    private fun initView() {
        id = findViewById(R.id.tedit_id)
        password = findViewById(R.id.tedit_password)
        passwordConfirm = findViewById(R.id.tedit_passowrd_confirm)
        name = findViewById(R.id.tedit_name)
        idTextLayout = findViewById(R.id.tlayout_id)
        passwordTextLayout = findViewById(R.id.tlayout_password)
        passwordConfirmTextLayout = findViewById(R.id.tlayout_password_confirm)
        nameTextLayout= findViewById(R.id.tlayout_name)
        nextBtn= findViewById(R.id.btn_next)
        nextBtn.isEnabled=false
    }

    private fun idValidation() {
        val idRegex = Regex("""^[a-z0-9-_]*$""")
        id.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(idInput: Editable?) {
                idInput?.let {
                    if (it.length < 5) {
                        idTextLayout.error = "아이디는 5자 이상 이어야 합니다"
                        idFlag=false
                    } else if (!(it.matches(idRegex))) {
                        idTextLayout.error = "아이디는 영소문자, 숫자, 특수문자-_로 구성되야 합니다"
                        idFlag=false
                    } else  {
                        idTextLayout.error = null
                        idTextLayout.helperText = "사용가능한 아이디입니다"
                        idFlag=true
                    }
                    checkInputAllInformation()
                }
            }


        })
    }

    private fun passwordValidation() {

        val passwordRegex =
            Regex("""^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$""")
        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(passwordInput: Editable?) {
                passwordInput?.let {
                    if (it.length < 8) {
                        passwordTextLayout.error = "비밀번호는 8자 이상 이어야 합니다"
                        passwordFlag=false
                    } else if (!(it.matches(passwordRegex))) {
                        passwordTextLayout.error = "비밀번호는 영대소문자, 숫자, 특수문자가 각각 하나씩이상 포함되어야합니다"
                        passwordFlag=false
                    } else {
                        passwordTextLayout.error = null
                        passwordTextLayout.helperText = "사용가능한 비밀번호입니다"
                        passwordFlag = true

                    }
                    checkInputAllInformation()
                }
            }
        })
    }

    private fun passwordConfirmValidation() {
        val curPassword = password.text
        passwordConfirm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(passwordConfirmInput: Editable?) {
                passwordConfirmInput?.let {
                    if (it.toString() == curPassword.toString()) {
                        passwordConfirmTextLayout.error = null
                        passwordConfirmTextLayout.helperText = "비밀번호가 일치합니다"
                        passwordConfirmFlag=true
                    } else {
                        passwordConfirmTextLayout.error = "비밀번호가 일치하지 않습니다"
                        passwordConfirmFlag=false
                    }
                    checkInputAllInformation()
                }
            }
        })
    }

    private fun nameValidation(){
        name.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(nameInput: Editable?) {
                nameInput?.let{
                    if(it.isEmpty()){
                        nameTextLayout.error="이름은 필수 입력 항목입니다"
                        nameFlag=false
                    }
                    else{
                        nameTextLayout.error=null
                        nameFlag=true
                    }
                    checkInputAllInformation()
                }
            }


        })
    }


    private fun checkValidation(){
        idValidation()
        passwordValidation()
        passwordConfirmValidation()
        nameValidation()
    }

    private fun checkInputAllInformation(){
        nextBtn.isEnabled = idFlag&&passwordFlag&&passwordConfirmFlag&&nameFlag
    }

    private fun setNextBtnListener(){
        nextBtn.setOnClickListener {
            val intent= Intent(this, PersonalInformationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.slide_right_enter)
        }
    }

}
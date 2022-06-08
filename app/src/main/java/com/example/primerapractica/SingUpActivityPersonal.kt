package com.example.primerapractica

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class SingUpActivityPersonal : AppCompatActivity() {

    private lateinit var txtBirth: TextInputEditText
    private lateinit var  btnBirth: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up_personal)


        txtBirth= findViewById(R.id.rBirthday)
        btnBirth=findViewById(R.id.btnCalendar)

        val buttonNext= findViewById<Button>(R.id.btnNext)

        buttonNext.setOnClickListener{
            val intent= Intent(applicationContext,SingUpActivity::class.java).apply{}
            startActivity(intent)
        }

        val myCalendar= Calendar.getInstance()
        val datePicker= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)

        }

        btnBirth.setOnClickListener {
            DatePickerDialog(this,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }


    }

    private fun updateLable(myCalendar: Calendar) {
        val myFormat="dd-mm-yyy"
        val Df=SimpleDateFormat(myFormat,Locale.US)
        txtBirth.setText(Df.format(myCalendar.time))

    }
}
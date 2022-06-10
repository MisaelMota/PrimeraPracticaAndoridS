package com.example.primerapractica

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.view.isEmpty
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class SingUpActivityPersonal : AppCompatActivity() {

    private lateinit var txtBirth: EditText
    private lateinit var  btnBirth: Button
    private lateinit var  txtName: EditText
    private lateinit var  txtLastName: EditText
    private lateinit var  txtCountry: AppCompatSpinner
    private lateinit var  txtGender: AppCompatSpinner




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up_personal)



        dListCountry();
        dListGender();



        txtBirth= findViewById(R.id.rBirthday)
        btnBirth=findViewById(R.id.btnCalendar)

        val buttonNext= findViewById<Button>(R.id.btnNext)

        buttonNext.setOnClickListener{
           validate()
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
        val myFormat="mm-dd-yyy"
        val Df=SimpleDateFormat(myFormat,Locale.US)
        txtBirth.setText(Df.format(myCalendar.time))

    }

    private fun dListCountry(){
        val spinner = findViewById<Spinner>(R.id.spnCountry)
        val list = resources.getStringArray(R.array.Countries)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
        spinner.adapter=adapter;


    }

    private fun dListGender(){
        val spinner = findViewById<Spinner>(R.id.spnGender)
        val list = resources.getStringArray(R.array.Genders)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
        spinner.adapter=adapter;
    }

    private fun validatePersonaInformation():Boolean{

        txtName= findViewById<EditText>(R.id.rName)
        txtLastName= findViewById<EditText>(R.id.rLastName)
        txtBirth= findViewById<EditText>(R.id.rBirthday)
        txtGender= findViewById<AppCompatSpinner>(R.id.spnGender)
        txtCountry= findViewById<AppCompatSpinner>(R.id.spnCountry)
        val layGender= findViewById<TextInputLayout>(R.id.LGender)
        val layCountry= findViewById<TextInputLayout>(R.id.LCountry)
        val layName= findViewById<TextInputLayout>(R.id.LName)
        val layLastn= findViewById<TextInputLayout>(R.id.LlastName)
        val layBirth= findViewById<TextInputLayout>(R.id.LBirthday)

        return if (txtName.text.isEmpty()){
            layName.error="Field can not be empty"
            false
        } else if (txtLastName.text.isEmpty()){
            layLastn.error="Field can not be empty"
            false
        }else if (txtBirth.text.isEmpty()){
            layBirth.error="Field can not be empty"
            false
        }else{
            layName.error=null
            layLastn.error=null
            layBirth.error=null
            layGender.error=null
            layCountry.error=null
            true

        }




    }

    fun validate(){



        val result= validatePersonaInformation()

        if(!result){
            return
        }
        else{
            val intent= Intent(applicationContext,SingUpActivity::class.java).apply{}
            intent.putExtra("name",txtName.text.toString())
            intent.putExtra("Lastname",txtLastName.text.toString())
            intent.putExtra("Birth",txtBirth.text.toString())
            intent.putExtra("Gender",txtGender.getItemAtPosition(txtGender.selectedItemPosition).toString())
            intent.putExtra("Country",txtCountry.getItemAtPosition(txtCountry.selectedItemPosition).toString())
            startActivity(intent)
        }


    }




}
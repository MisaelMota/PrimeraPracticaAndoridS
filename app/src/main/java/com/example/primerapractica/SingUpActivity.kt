package com.example.primerapractica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class SingUpActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth;
    private lateinit var layouttxt: TextInputLayout
    private lateinit var layoutpass: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        auth=FirebaseAuth.getInstance();


        val btnSingUp= findViewById<Button>(R.id.btnSingUp)
        val txtUsername= findViewById<EditText>(R.id.rEmail)
        val txtPassword= findViewById<EditText>(R.id.rPassword)

        btnSingUp.setOnClickListener {
           validate();
        }





        val buttonlog= findViewById<Button>(R.id.AlCount)

        buttonlog.setOnClickListener{
            val intent= Intent(applicationContext,LoginActivity::class.java).apply{}
            startActivity(intent)
        }
    }

    fun createUser(email:String,password:String){

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Sing Up complete successful",Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this,"the password should be have 6 characters min",Toast.LENGTH_LONG).show()
                }
            }
    }

    fun validateEmail():Boolean{
        val txtUsername= findViewById<EditText>(R.id.rEmail)
        layouttxt= findViewById(R.id.Username2)


        return if (txtUsername.text.isEmpty()){
           // txtUsername.error="Field can not be empty"
            layouttxt.error="Field can not be empty"
             false
        }else if (!PatternsCompat.EMAIL_ADDRESS.matcher(txtUsername.text.trim()).matches()){
            //txtUsername.error="Please enter a valid email address"
            layouttxt.error="Please enter a valid email address"
            false

        }else{
            //txtUsername.error=null
            layouttxt.error=null
            true
        }

    }

    fun validatePassword():Boolean{
        val txtPassword= findViewById<EditText>(R.id.rPassword)
        layoutpass= findViewById(R.id.Password2)
        val passwordPatter= Pattern.compile(
            "^" +

        "(?=.*[@#$%^&+=])" +     // at least 1 special character

        "(?=\\S+$)" +                     // no white spaces

        ".{6,}" +                              // at least 6 characters

        "$"
        )

        return if (txtPassword.text.isEmpty()){
            //txtPassword.error="Field can not be empty"
            layoutpass.error="Field can not be empty"
            false
        }else if (!passwordPatter.matcher(txtPassword.text.trim()).matches()){
            //txtPassword.error="Password is to weak"
            layoutpass.error="Password is to weak"
            false

        }else{
            //txtPassword.error=null
            layoutpass.error=null
            true
        }
    }

    fun validate(){

        val txtUsername= findViewById<EditText>(R.id.rEmail)
        val txtPassword= findViewById<EditText>(R.id.rPassword)

        val result= arrayOf(validateEmail(),validatePassword())

        if(false in result){
            return
        }
        createUser(txtUsername.text.trim().toString(),txtPassword.text.trim().toString())

    }


}
package com.example.primerapractica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= FirebaseAuth.getInstance();

        val buttonLogin= findViewById<Button>(R.id.LogGo)
        val buttonSin= findViewById<Button>(R.id.NewUser)
        val email= findViewById<EditText>(R.id.lEmail)
        val password=findViewById<EditText>(R.id.lPassword)
        val name= findViewById<EditText>(R.id.lName)

        buttonLogin.setOnClickListener {
            if (email.text.isNotEmpty() && password.text.isNotEmpty() && name.text.isNotEmpty()){
                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent= Intent(applicationContext,UserProfile::class.java).apply{}
                            intent.putExtra("LoginEmail",email.text.toString())
                            intent.putExtra("Name",name.text.toString())
                            intent.putExtra("LoginPassword",password.text.toString())
                            startActivity(intent)

                        } else {
                            Toast.makeText(this,"Authentication failed", Toast.LENGTH_LONG).show()
                        }
                    }

            }else{
                val layUsername= findViewById<TextInputLayout>(R.id.Username)
                val layPassword= findViewById<TextInputLayout>(R.id.Password)
                val layname= findViewById<TextInputLayout>(R.id.Layname)

                layUsername.error="Field can not be empty"
                layPassword.error="Field can not be empty"
                layname.error="Field can not be empty"

            }
        }

        buttonSin.setOnClickListener{
            val intent= Intent(applicationContext,SingUpActivityPersonal::class.java).apply{}
            startActivity(intent)
        }

    }





}
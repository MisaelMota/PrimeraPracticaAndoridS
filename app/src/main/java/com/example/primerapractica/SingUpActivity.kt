package com.example.primerapractica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SingUpActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        auth=FirebaseAuth.getInstance();

        val btnSingUp= findViewById<Button>(R.id.btnSingUp)
        val txtUsername= findViewById<EditText>(R.id.rEmail)
        val txtPassword= findViewById<EditText>(R.id.rPassword)

        btnSingUp.setOnClickListener {
            if(txtUsername.text.trim().toString().isNotEmpty() || txtPassword.text.trim().toString().isNotEmpty()){

                createUser(txtUsername.text.trim().toString(),txtPassword.text.trim().toString())

            }
            else{
                Toast.makeText(this,"Imput Required",Toast.LENGTH_LONG).show()
            }
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




}
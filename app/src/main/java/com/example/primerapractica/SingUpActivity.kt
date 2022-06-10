package com.example.primerapractica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.util.PatternsCompat
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

class SingUpActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth;
    private lateinit var layouttxt: TextInputLayout
    private lateinit var layoutpass: TextInputLayout

    //Variables para los campos de los formularios
    private lateinit var Uname: String
    private lateinit var UlastName: String
    private lateinit var UBirthday: String
    private lateinit var Ugender: String
    private lateinit var Ucountry: String
    private lateinit var Uemail: EditText
    private lateinit var Upassword: EditText

    //variable para la base de datos
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        auth=FirebaseAuth.getInstance();


        val btnSingUp= findViewById<Button>(R.id.btnSingUp)
        Uname= intent.getStringExtra("name")!!
        UlastName=intent.getStringExtra("Lastname")!!
        UBirthday=intent.getStringExtra("Birth")!!
        Ugender=intent.getStringExtra("Gender")!!
        Ucountry=intent.getStringExtra("Country")!!
        Uemail=findViewById(R.id.rEmail)
        Upassword=findViewById(R.id.rPassword)

        dbRef= FirebaseDatabase.getInstance().getReference("Users")




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

        val userID=dbRef.push().key!!
        val userEmail= Uemail.text.toString()
        val userPassword= Upassword.text.toString()
        val userName=Uname
        val userLastname= UlastName
        val userBirthday= UBirthday
        val userGender= Ugender
        val userCountry= Ucountry

        val user= UsersModel(userID,userName,userLastname,userBirthday,userGender,userCountry,userEmail,userPassword)


        val result= arrayOf(validateEmail(),validatePassword())

        if(false in result){
            return
        }else{


            dbRef.child(userID).setValue(user)
                .addOnCompleteListener {
                    createUser(userEmail,userPassword)
                    Toast.makeText(this,"Data inserted successful",Toast.LENGTH_LONG).show()
                }.addOnFailureListener { err ->
                    Toast.makeText(this,"Error: ${err.message}",Toast.LENGTH_LONG).show()
                }


        }





    }


}
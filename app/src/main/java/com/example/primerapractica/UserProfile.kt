package com.example.primerapractica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserProfile : AppCompatActivity() {

    private lateinit var loginEmail:String
    private lateinit var loginName:String
    private lateinit var loginPassword:String
    private lateinit var database: DatabaseReference
    private lateinit var name: TextView
    private lateinit var Lastname: TextView
    private lateinit var Birthday: TextView
    private lateinit var Gender: TextView
    private lateinit var Country: TextView
    private lateinit var Email: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        loginEmail=intent.getStringExtra("LoginEmail")!!
        loginName=intent.getStringExtra("Name")!!
        loginPassword=intent.getStringExtra("LoginPassword")!!

        name= findViewById(R.id.txtname)
        Lastname= findViewById(R.id.txtLastName)
        Birthday= findViewById(R.id.txtBirthday)
        Gender= findViewById(R.id.txtGender)
        Country= findViewById(R.id.txtCountry)
        Email= findViewById(R.id.txtEmail)



            readData(loginName);





    }

    private fun readData(loginName: String) {
        database=FirebaseDatabase.getInstance().getReference("Users")
        database.child(loginName).get().addOnSuccessListener {

            if (it.exists()){

                val dName=it.child("userName").value
                val dLastName=it.child("userLastName").value
                val dBirthday=it.child("birthday").value
                val dGender=it.child("gender").value
                val dCountry=it.child("country").value
                val dEmail=it.child("email").value

                name.text= dName.toString()
                Lastname.text=dLastName.toString()
                Birthday.text=dBirthday.toString()
                Gender.text=dGender.toString()
                Country.text=dCountry.toString()
                Email.text=dEmail.toString()

            }else{
                Toast.makeText(this,"User Doesn't Exists", Toast.LENGTH_LONG).show()
            }


        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
        }

    }


}
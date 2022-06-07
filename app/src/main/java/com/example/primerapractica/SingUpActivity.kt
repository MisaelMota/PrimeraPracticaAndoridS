package com.example.primerapractica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SingUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)


        val buttonlog= findViewById<Button>(R.id.AlCount)

        buttonlog.setOnClickListener{
            val intent= Intent(applicationContext,LoginActivity::class.java).apply{}
            startActivity(intent)
        }
    }


}
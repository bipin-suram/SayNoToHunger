package com.example.saynotohunger

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saynotohunger.R.layout.volunteer_register

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class VolunteerRegister: AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var email: EditText
    lateinit var pwd: EditText
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(volunteer_register)

        findViewById<Button>(R.id.goToVolunteerLogin).setOnClickListener {
            startActivity(Intent(this, VolunteerLogin::class.java))
        }

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("User")

        register()

    }

    private fun register(){

        email = findViewById(R.id.emailID)
        pwd = findViewById(R.id.password)

        findViewById<Button>(R.id.registerNew).setOnClickListener {

            if (email.text.toString().isEmpty()){
                email.error = "Please enter Email"
                email.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
                email.error = " Please enter valid Email"
                email.requestFocus()
                return@setOnClickListener
            }

            if(pwd.text.toString().isEmpty()){
                pwd.error = "Enter password"
                pwd.requestFocus()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val currentUser = auth.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child("Email")?.setValue(email.text.toString())
                        currentUserDb?.child("password")?.setValue(pwd.text.toString())
                        startActivity(Intent(this, VolunteerLogin::class.java))
                        finish()
                        Toast.makeText(baseContext, "Sign up success",Toast.LENGTH_SHORT).show()
                    }

                    else {
                        Toast.makeText(baseContext, "Sign up failed. Try after sometime",Toast.LENGTH_SHORT).show()
                    }
                }


        }
    }

}









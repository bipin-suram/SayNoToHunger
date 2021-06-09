package com.example.saynotohunger

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class VolunteerLogin: AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var email: EditText
    lateinit var pwd: EditText
    lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.volunteer_login)

        findViewById<Button>(R.id.goToVolunteerRegister).setOnClickListener {
            startActivity(Intent(this, VolunteerRegister::class.java))
        }

        login()

    }

    private fun login() {

        email = findViewById(R.id.emailIDLogin)
        pwd = findViewById(R.id.passwordLogin)
        loginButton = findViewById(R.id.login)


        loginButton.setOnClickListener {

            if (email.text.toString().isEmpty()) {
                email.error = "Please enter Email"
                email.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                email.error = "Please enter valid Email"
                email.requestFocus()
                return@setOnClickListener
            }

            if (pwd.text.toString().isEmpty()) {
                pwd.error = "Enter password"
                pwd.requestFocus()
                return@setOnClickListener
            }

            auth = FirebaseAuth.getInstance()

            auth.signInWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, FoodRequests::class.java))
                        Toast.makeText(baseContext, "Login success",Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else {
                        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
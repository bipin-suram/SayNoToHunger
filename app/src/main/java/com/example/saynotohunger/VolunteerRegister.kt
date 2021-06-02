package com.example.saynotohunger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class VolunteerRegister: AppCompatActivity () {
    private lateinit var mobileNo: EditText
    private lateinit var fullName: EditText
    private lateinit var emailID: EditText
    private lateinit var password: EditText
    private lateinit var registerButtton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.volunteer_register)

        findViewById<Button>(R.id.goToVolunteerLogin).setOnClickListener {
            startActivity(Intent(this, VolunteerLogin::class.java))
        }
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        dbRef = database.reference


        fullName = findViewById(R.id.fullName) as EditText
        emailID = findViewById(R.id.email) as EditText
        mobileNo = findViewById(R.id.mobileNo) as EditText
        password = findViewById(R.id.password) as EditText
        registerButtton = findViewById(R.id.registerNew) as Button


        registerButtton.setOnClickListener() {
                    auth.createUserWithEmailAndPassword(emailID.text.toString(), password.text.toString())
                        .addOnCompleteListener { task: Task<AuthResult> ->
                            if (task.isSuccessful) {
                                val userId = auth.currentUser?.uid
                                val registerRef = dbRef.child("user").child(userId.toString())
                                val user = User(fullName.text.toString(), mobileNo.text.toString())
                                registerRef.setValue(user).addOnSuccessListener(){
                                    val intent = Intent(this, HomePage::class.java)
                                    startActivity(intent)
                                    finish()
                            }
                        }
                }


        }

    }
}





package com.example.saynotohunger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saynotohunger.Models.Requests
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewFoodRequest: AppCompatActivity() {


    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var Description: EditText
    private lateinit var Mobile: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_food_request)

        findViewById<Button>(R.id.registerHome).setOnClickListener {
            startActivity(Intent(this, HomePage::class.java))
        }

        findViewById<Button>(R.id.registerAvailableFood).setOnClickListener {
            startActivity(Intent(this, AvailableFood::class.java))
        }

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Requests")


        findViewById<Button>(R.id.foodRequest).setOnClickListener {
           Request()

            startActivity(Intent(this, FoodRequests::class.java))

        }

    }

    private fun Request() {

        Description = findViewById(R.id.foodDescription)
        Mobile = findViewById(R.id.mobile)

        if(Description.text.toString().isNotEmpty() && Mobile.text.toString().isNotEmpty()) {
            val model = Requests(Description.text.toString(), Mobile.text.toString())
            val id = reference.push().key

            reference.child(id!!).setValue(model)
            Description.setText("")
            Mobile.setText("")


            finish()
        }
      else{
            Toast.makeText(baseContext, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
}
}

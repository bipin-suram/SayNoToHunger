package com.example.saynotohunger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saynotohunger.Models.Donations
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DonatePage: AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var item: EditText
    private lateinit var quantity: EditText
    private lateinit var time: EditText
    private lateinit var cost: EditText
    private lateinit var location: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donate_page)

        findViewById<Button>(R.id.availableFood).setOnClickListener {
            startActivity(Intent(this, AvailableFood::class.java))
        }


        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Donations")

        findViewById<Button>(R.id.donationPost).setOnClickListener {
            Donate()

            startActivity(Intent(this, AvailableFood::class.java))

        }
    }

    private fun Donate() {

        item = findViewById(R.id.description)
        quantity = findViewById(R.id.foodQuantity)
        time = findViewById(R.id.foodAvailableTime)
        cost = findViewById(R.id.foodCost)
        location = findViewById(R.id.foodLocation)

        if (item.text.toString().isNotEmpty() && quantity.text.toString()
                .isNotEmpty() && time.text.toString().isNotEmpty()
            && cost.text.toString().isNotEmpty() && location.text.toString().isNotEmpty()
        ) {
            val model = Donations(
                item.text.toString(),
                quantity.text.toString(),
                time.text.toString(),
                cost.text.toString(),
                location.text.toString()
            )
            val id = reference.push().key

            reference.child(id!!).setValue(model)

            item.setText("")
            quantity.setText("")
            time.setText("")
            cost.setText("")
            location.setText("")

            finish()
        } else {
            Toast.makeText(baseContext, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }
}




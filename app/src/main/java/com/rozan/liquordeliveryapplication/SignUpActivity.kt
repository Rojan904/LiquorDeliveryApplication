package com.rozan.liquordeliveryapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import com.rozan.liquordeliveryapplication.db.CustomerDB
import com.rozan.liquordeliveryapplication.entity.Customer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var etFname:EditText
    private lateinit var etLname:EditText
    private lateinit var etDOB:EditText
    private lateinit var etUsername: EditText
    private lateinit var etEmail:EditText
    private lateinit var etPassword:EditText
    private lateinit var btnSignup:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etFname=findViewById(R.id.etFname)
        etLname=findViewById(R.id.etLname)
        etDOB=findViewById(R.id.etDOB)
        etUsername=findViewById(R.id.etUsername)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        btnSignup=findViewById(R.id.btnSignup)

        etDOB.setOnClickListener {
            loadCalendar()
        }

        btnSignup.setOnClickListener {
            registerCustomer()
        }
    }



    private fun loadCalendar() {
        val c= Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog=DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
            view,year,monthOfYear,dayOfMonth->
            etDOB.setText("$dayOfMonth/${monthOfYear+1}/$year")

        },
        year,
        month,
        day)
        datePickerDialog.show()
    }

    private fun registerCustomer() {
        val fname=etFname.text.toString()
        val lname=etLname.text.toString()
        val dob=etDOB.text.toString()
        val username=etUsername.text.toString()
        val email=etEmail.text.toString()
        val password=etPassword.text.toString()

        val customer= Customer(fname,lname,dob, username, email, password) //providing parameters to Customer

        //Performing task in background thread so using CoroutineScope as it is light weight thread
        CoroutineScope(Dispatchers.IO).launch {
            CustomerDB
                .getInstance(this@SignUpActivity)
                .getCustomerDAO()
                .registerCustomer(customer)
            withContext(Main){
                Toast.makeText(this@SignUpActivity, "Customer registration successfull!", Toast.LENGTH_SHORT).show()
            }

        }

    }
}
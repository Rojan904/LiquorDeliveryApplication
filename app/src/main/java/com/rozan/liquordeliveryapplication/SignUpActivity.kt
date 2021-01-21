package com.rozan.liquordeliveryapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.EditText
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var etFname:EditText
    private lateinit var etLname:EditText
    private lateinit var etDOB:EditText
    private lateinit var etUsername: EditText
    private lateinit var etEmail:EditText
    private lateinit var etPassword:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etFname=findViewById(R.id.etFname)
        etLname=findViewById(R.id.etLname)
        etDOB=findViewById(R.id.etDOB)
        etUsername=findViewById(R.id.etUsername)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)

        etDOB.setOnClickListener {
            loadCalendar()
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
}
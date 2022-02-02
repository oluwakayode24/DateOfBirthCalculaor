package khay.kotlinlearning.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
        var tvSelectedDate : TextView? = null
        var tvAgeInHours : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //call button by id
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)

        // Set Button listener
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }   // end button Listener

    }   // end onCreate

    //creating a function that the button work on
    private fun clickDatePicker(){
        //Calling a Calendar class to be used DatePicker Dialog box
        val myCalendar = Calendar.getInstance()

        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)


        //create DatePicker Dialog box and set the listener
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "SelectedDay was $selectedDayOfMonth, SelectedMonth was ${selectedMonth +1}"
                    + " and  SelectedYear was $selectedYear", Toast.LENGTH_LONG).show()

            //creating the selectedDate in String format
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            //calling and setting the selectedDate TextView
            tvSelectedDate = findViewById(R.id.selectedDate)
            tvSelectedDate?.text = selectedDate

            //calling and setting the selectedDate TextView
            tvAgeInHours = findViewById(R.id.tvAgeInHours)

            //Using SimpleDateFormat to get date and use in calculation
            var sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            var theDate = sdf.parse(selectedDate)

            //date in minutes
            val selectedDateInMinutes = theDate.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())) //getting age in milliseconds

            val currentDateInMinutes = currentDate.time / 60000

            val differenceInAGe = currentDateInMinutes - selectedDateInMinutes

            tvAgeInHours?.text = differenceInAGe.toString() //putting differenceInAGe into TextView



        },
            year, month, day
        )

        //to avoid picking future dates
        dpd.datePicker.maxDate = System.currentTimeMillis() - 860000
        dpd.show()



    } //end clickDatePicker function
} //end mainActivity

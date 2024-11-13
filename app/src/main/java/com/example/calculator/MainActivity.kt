package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var timeInputET1: EditText
    private lateinit var timeInputET2: EditText
    private lateinit var plusBTN: Button
    private lateinit var minusBTN: Button
    private lateinit var resultTW: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        timeInputET1 = findViewById(R.id.timeInputET1)
        timeInputET2 = findViewById(R.id.timeInputET2)

        plusBTN = findViewById(R.id.plusBTN)
        minusBTN = findViewById(R.id.minusBTN)

        resultTW = findViewById(R.id.resultTW)

        plusBTN.setOnClickListener(this)
        minusBTN.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        var result = ""
        when(v.id) {
            R.id.plusBTN -> {
                val first = convertTimeToSeconds(timeInputET1.text.toString())
                val second = convertTimeToSeconds(timeInputET2.text.toString())
                resultTW.text = convertSecondsToTime(first + second)
            }
            R.id.minusBTN -> {
                val first = convertTimeToSeconds(timeInputET1.text.toString())
                val second = convertTimeToSeconds(timeInputET2.text.toString())
                resultTW.text = convertSecondsToTime(first - second)
            }
        }
    }

    private fun convertTimeToSeconds(time: String): Int {
        var seconds = 0
        var timeString = time
        if (timeString.contains('h')) {
            seconds = 3600 * timeString.takeWhile { it.isDigit() }.toInt()
            timeString = timeString.dropWhile { it.isDigit() }.drop(1)
        }
        if (timeString.contains('m')) {
            seconds += 60 * timeString.takeWhile { it.isDigit() }.toInt()
            timeString = timeString.dropWhile { it.isDigit() }.drop(1)
        }
        if (timeString.contains('s')) {
            seconds += timeString.takeWhile { it.isDigit() }.toInt()
        }
        return seconds
    }

    private fun convertSecondsToTime(seconds: Int): String {
        return "${seconds / 3600}h${seconds % 3600 / 60}m${seconds % 3600 % 60}s"
    }
}
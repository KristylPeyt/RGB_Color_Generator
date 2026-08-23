package com.example.rgbcolorgenerator

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var redInput: TextInputEditText
    private lateinit var greenInput: TextInputEditText
    private lateinit var blueInput: TextInputEditText

    private lateinit var redLayout: TextInputLayout
    private lateinit var greenLayout: TextInputLayout
    private lateinit var blueLayout: TextInputLayout

    private lateinit var colorPreview: View
    private lateinit var colorCode: TextView
    private lateinit var createButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        redInput = findViewById(R.id.etRed)
        greenInput = findViewById(R.id.etGreen)
        blueInput = findViewById(R.id.etBlue)

        redLayout = findViewById(R.id.redLayout)
        greenLayout = findViewById(R.id.greenLayout)
        blueLayout = findViewById(R.id.blueLayout)

        colorPreview = findViewById(R.id.colorPreview)
        colorCode = findViewById(R.id.tvColorCode)
        createButton = findViewById(R.id.btnCreateColor)

        createButton.setOnClickListener {
            createRGBColor()
        }
    }

    private fun createRGBColor() {

        val red = redInput.text.toString().trim().uppercase()
        val green = greenInput.text.toString().trim().uppercase()
        val blue = blueInput.text.toString().trim().uppercase()

        redLayout.error = null
        greenLayout.error = null
        blueLayout.error = null

        var valid = true

        // Validate Red
        if (!isValidHex(red)) {
            redLayout.error = "Enter 2 hexadecimal characters"
            valid = false
        }

        // Validate Green
        if (!isValidHex(green)) {
            greenLayout.error = "Enter 2 hexadecimal characters"
            valid = false
        }

        // Validate Blue
        if (!isValidHex(blue)) {
            blueLayout.error = "Enter 2 hexadecimal characters"
            valid = false
        }

        if (!valid) {
            return
        }

        // Combine the three channels
        val hexColor = "#$red$green$blue"

        // Convert hexadecimal string to Android Color
        val color = Color.parseColor(hexColor)

        // Display the color
        colorPreview.setBackgroundColor(color)
        colorCode.setBackgroundColor(color)

        // Display the hexadecimal color
        colorCode.text = hexColor
    }

    private fun isValidHex(value: String): Boolean {

        if (value.length != 2) {
            return false
        }

        return value.matches(Regex("[0-9A-Fa-f]{2}"))
    }
}
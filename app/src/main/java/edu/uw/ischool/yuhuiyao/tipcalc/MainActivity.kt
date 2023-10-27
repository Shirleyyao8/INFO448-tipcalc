//package edu.uw.ischool.yuhuiyao.tipcalc
//
//import android.os.Bundle
//import android.text.Editable
//import android.text.InputFilter
//import android.text.SpannableStringBuilder
//import android.text.Spanned
//import android.text.TextWatcher
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import edu.uw.ischool.yuhuiyao.tipcalc.R.id.editText
//import android.view.View
//import android.view.inputmethod.InputMethodManager
//import android.content.Context
//import android.util.Log
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val inputText = findViewById<EditText>(editText)
//        val tipButton = findViewById<Button>(R.id.button)
//
//        val decimalDigits = 2 // Adjust this value to set the desired number of decimal places
//        inputText.filters = arrayOf(DecimalInputFilter(decimalDigits))
//
//        val textWatcher = object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                // Check if the EditText has text
//                val userInput = inputText.toString().trim()
//
//                // Enable or disable the button based on the text
//               tipButton.setEnabled(!userInput.isEmpty())
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//        }
//        inputText.addTextChangedListener(textWatcher)
//
//
//        tipButton.setOnClickListener {
//
//
//            val userInput = inputText.text.toString().trim()
//            inputText.setText("$" + userInput)
//            inputText.setSelection(inputText.text.length)
//
//            val amount = userInput.toDouble()
//            val tip = amount * 0.15
//
//
//            // Format the tip as a string with two decimal places
//            val formattedTip = String.format("%.2f", tip)
//
//
//            Toast.makeText(this, "$$formattedTip", Toast.LENGTH_SHORT).show()
//            }
//    }
//
//
//    class DecimalInputFilter(private val decimalDigits: Int) : InputFilter {
//
//        override fun filter(
//            source: CharSequence,
//            start: Int,
//            end: Int,
//            dest: Spanned,
//            dstart: Int,
//            dend: Int
//        ): CharSequence? {
//            val builder = SpannableStringBuilder(dest)
//            builder.replace(
//                dstart, dend,
//                source.subSequence(start, end), 0, end - start
//            )
//
//            val matcher = Regex("^\\d*\\.?\\d{0,$decimalDigits}").toPattern().matcher(builder)
//
//            if (!matcher.matches()) {
//                return dest.subSequence(dstart, dend)
//            }
//
//            return null
//        }
//    }
//
//
//}
//

package edu.uw.ischool.yuhuiyao.tipcalc

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager

class MainActivity : AppCompatActivity() {

    private var inputAmount: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputText = findViewById<EditText>(R.id.editText)
        val tipButton = findViewById<Button>(R.id.button)

        val decimalDigits = 2 // Adjust this value to set the desired number of decimal places
        inputText.filters = arrayOf(DecimalInputFilter(decimalDigits))

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Check if the EditText has text
                val userInput = inputText.text.toString().trim()

                // Store the input amount
                inputAmount = userInput

                // Enable or disable the button based on the text
                tipButton.isEnabled = !userInput.isEmpty()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        inputText.addTextChangedListener(textWatcher)

        tipButton.setOnClickListener {
            // Retrieve the input amount from the stored variable
            val userInput = inputAmount
            inputText.setText("$" + userInput)
            inputText.setSelection(inputText.text.length)

            val amount = userInput.toDouble()
            val tip = amount * 0.15

            // Format the tip as a string with two decimal places
            val formattedTip = String.format("%.2f", tip)

            Toast.makeText(this, "$$formattedTip", Toast.LENGTH_SHORT).show()
        }
    }

    class DecimalInputFilter(private val decimalDigits: Int) : InputFilter {
        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            val builder = SpannableStringBuilder(dest)
            builder.replace(
                dstart, dend,
                source.subSequence(start, end), 0, end - start
            )

            val matcher = Regex("^\\d*\\.?\\d{0,$decimalDigits}").toPattern().matcher(builder)

            if (!matcher.matches()) {
                return dest.subSequence(dstart, dend)
            }

            return null
        }
    }
}


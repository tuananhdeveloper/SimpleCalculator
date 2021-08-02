package com.tuananh.app.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val ERROR = "Lỗi"
    private val multiply = "*"
    private val divide = "/"
    private val add = "+"
    private val subtract = "-"
    private val point = "."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun handleNumberBtnClick(v: View) {
        var button = v as Button
        appendTextToExpressionText(button.text.toString())
    }

    fun handleOperatorBtnClick(v: View) {
        var button = v as Button
        appendTextToExpressionText(button.text.toString())
    }

    fun handleActionBtnClick(v: View) {
        var button = v as Button
        when(button.text.toString()) {
            getString(R.string.text_clear) -> clearScreen()
            getString(R.string.text_delete) -> delete()
            getString(R.string.text_equality) -> calculate()
        }
    }

    private fun calculate() {
        var expression = text_expression.text.toString()
        if(expression.isNotEmpty()) {
            expression = expression.replace(getString(R.string.text_add), " $add ")
                .replace(getString(R.string.text_sub), " $subtract ")
                .replace(getString(R.string.text_multiply), " $multiply ")
                .replace(getString(R.string.text_divide), " $divide ")
                .replace(getString(R.string.text_point), point)
            try {
                var result = Calculator().cal(expression)
                text_result.setText(result.toString())
            }
            catch (e: Exception) {
                text_result.setText(ERROR)
            }

        }
    }

    private fun delete() {
        if(text_expression.text.toString().isNotEmpty()) {
            text_expression.text.delete(text_expression.text.length - 1, text_expression.text.length)
        }
    }

    private fun clearScreen() {
        text_expression.setText("")
        text_result.setText("")
    }


    private fun appendTextToExpressionText(text: String) {
        text_expression.append(text)
    }
}
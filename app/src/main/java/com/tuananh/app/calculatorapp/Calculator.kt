package com.tuananh.app.calculatorapp

import java.util.Stack

class Calculator {

    private val valueStack: Stack<Double> = Stack()
    private val operatorStack: Stack<String> = Stack()

    private fun applyOperatorAndPushValueToStack() {
        val secondNumber = valueStack.pop()
        val firstNumber = valueStack.pop()
        val operator = operatorStack.pop()
        valueStack.push(applyOperator(firstNumber, secondNumber, operator))
    }

    private fun applyOperator(num1: Double, num2: Double, operator: String): Double =
        when (operator) {
            add -> num1 + num2
            subtract -> num1 - num2
            multiply -> num1 * num2
            divide -> num1 / num2
            else -> 0.0
        }

    private fun String.isDigit() = this.toDoubleOrNull()?.let { true } ?: false

    private fun priority(operator: String): Int {
        if (operator == add || operator == subtract) return 1
        if (operator == multiply || operator == divide) return 2
        return 0
    }

    fun cal(expression: String): Double {
        val tokens = expression.trim().split(" ")
        for (token in tokens) {
            if (token == empty) continue
            when {
                token.isDigit() -> {
                    valueStack.push(token.toDouble())
                }
                token == leftParenthesis -> {
                    operatorStack.push(token)
                }
                token == rightParenthesis -> {
                    while (operatorStack.peek() != leftParenthesis) {
                        applyOperatorAndPushValueToStack()
                    }
                    operatorStack.pop()
                }
                else -> {
                    while (!operatorStack.empty() && priority(operatorStack.peek()) >= priority(token)) {
                        applyOperatorAndPushValueToStack()
                    }
                    operatorStack.push(token)
                }
            }
        }

        while (!operatorStack.empty()) {
            applyOperatorAndPushValueToStack()
        }

        return valueStack.pop()
    }

    companion object {
        private const val multiply = "*"
        private const val divide = "/"
        private const val add = "+"
        private const val subtract = "-"
        private const val leftParenthesis = "("
        private const val rightParenthesis = ")"
        private const val empty = ""
    }
}

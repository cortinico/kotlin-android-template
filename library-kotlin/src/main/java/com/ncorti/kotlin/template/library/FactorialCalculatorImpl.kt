package com.ncorti.kotlin.template.library

internal class FactorialCalculatorImpl : FactorialCalculator {

    override fun computeFactorial(input: Int) = computeFactorialRec(input)

    private tailrec fun computeFactorialRec(input: Int, temp: Long = 1L): Long =
        when {
            input < 0 -> error("Factorial is not defined for negative numbers")
            input == 0 -> temp
            else -> computeFactorialRec(input - 1, temp * input)
        }
}

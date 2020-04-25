package com.ncorti.kotlin.template.library

object FactorialCalculator {

    tailrec fun computeFactorial(input: Int, temp: Long = 1L): Long =
        when {
            input < 0 -> error("Factorial is not defined for negative numbers")
            input == 0 -> temp
            else -> computeFactorial(input - 1, temp * input)
        }
}

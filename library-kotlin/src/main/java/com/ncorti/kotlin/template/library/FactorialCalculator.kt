package com.ncorti.kotlin.template.library

object FactorialCalculator {
    private const val MAX_FACTORIAL_64BIT = 20

    tailrec fun computeFactorial(input: Long, temp: Long = 1L): Long =
        when {
            input < 0 -> error("Factorial is not defined for negative numbers")
            input > MAX_FACTORIAL_64BIT -> error("Only a factorial up to 20 can fit in a 64-bit Long")
            input == 0L -> temp
            else -> computeFactorial(input - 1, temp * input)
        }
}

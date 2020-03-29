package com.ncorti.kotlin.template.library

import java.lang.Exception
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FactorialCalculatorImplTest {

    private val calculator = FactorialCalculatorProvider.calculator

    @Test
    fun computeFactorial_withNegative_raiseException() {
        assertThrows(Exception::class.java) {
            calculator.computeFactorial(-1)
        }
    }

    @Test
    fun computeFactorial_forZero() {
        assertEquals(1, calculator.computeFactorial(0))
    }

    @Test
    fun computeFactorial_forFive() {
        assertEquals(120, calculator.computeFactorial(5))
    }
}

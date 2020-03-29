package com.ncorti.kotlin.template.library

object FactorialCalculatorProvider {

    val calculator: FactorialCalculator by lazy { FactorialCalculatorImpl() }
}

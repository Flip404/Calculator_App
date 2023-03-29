package com.example.calculator

data class InitialDataState(
  val number1: String = "",
  val number2: String = "",
  val operator: Operator? = null,
  val displayValue: String = "",
)
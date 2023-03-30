package com.example.calculator

data class InitialDataState(
  val numbersList: MutableList<String> = mutableListOf<String>(),
  val operatorList: MutableList<Operator> = mutableListOf<Operator>(),
  val displayValue: String = "",
)
package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.log

class CalculatorViewModel : ViewModel() {

  var state by mutableStateOf(InitialDataState())
      private  set

  private val MAX_NUM_LENGTH = 15

  fun onClickButton(calculatorType: CalculatorType, number: Int, operator: Operator) {
    when(calculatorType){
      CalculatorType.Number -> enterNumber(number)
      CalculatorType.Decimal -> addDecimal()
      CalculatorType.Operation -> enterOperator(operator)
      CalculatorType.Calculate -> calculate()
      CalculatorType.Clean -> state = InitialDataState()
      CalculatorType.Delete -> delete()
    }
  }

  private fun enterNumber(number: Int) {
    if(state.operator == null){
      if(state.number1.length >= MAX_NUM_LENGTH){
        updateView()
        return
      }else{
        state = state.copy(number1 = state.number1 + number)
        updateView()
        return
      }
    }else {
      if(state.number2.length >= MAX_NUM_LENGTH){
        updateView()
        return
      }else{
        state = state.copy(number2 = state.number2 + number)
        updateView()
        return
      }
    }
  }

  private fun enterOperator(operator: Operator) {
    if(state.number1.isNotEmpty()){
      state = state.copy(operator = operator)
      updateView()
    }
  }

  private fun addDecimal() {
    if(state.operator == null && !state.number1.contains(".") && state.number1.isNotBlank()){
      state = state.copy(number1 = state.number1 + ".")
      updateView()
      return
    }else if(state.operator != null && !state.number2.contains(".") && state.number2.isNotBlank()){
      state = state.copy(number2 = state.number2 + ".")
      updateView()
      return
    }
  }

  private fun calculate() {
    val number1 = state.number1.toDoubleOrNull();
    val number2 = state.number2.toDoubleOrNull();
    if(number1 != null && number2 != null && state.operator != null){
      val result = when(state.operator){
        Operator.Add -> number1 + number2
        Operator.Subtract -> number1 - number2
        Operator.Multiply -> number1 * number2
        Operator.Divide -> number1 / number2
        Operator.None -> ""
        else -> ""
      }
      state = InitialDataState()
      state = state.copy(
        number1 = result.toString().take(20)
      )
      updateView()
    }else {
      updateView()
      return
    }
  }

  private fun delete() {
    when{
      state.number2.isNotEmpty() -> state = state.copy(number2 = state.number2.dropLast(1))
      state.operator != null -> state = state.copy(operator = null)
      state.number1.isNotEmpty() -> state = state.copy(number1 = state.number1.dropLast(1))
    }
    updateView()
  }

  private fun updateView() {
    val operator : String = when(state.operator) {
      Operator.Add -> " + "
      Operator.Subtract -> " - "
      Operator.Multiply -> " x "
      Operator.Divide -> " / "
      Operator.None -> ""
      else -> ""
    }
    state = state.copy(displayValue = state.number1 + operator + state.number2)
    return
  }
}
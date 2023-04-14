package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

  var state by mutableStateOf(InitialDataState())
      private  set

  private val maxNumber = 15

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
    val operatorSize = state.operatorList.size
    val list = state.numbersList
    when{
      (state.numbersList.isEmpty()) -> {
        list.add(number.toString())
        state = state.copy(numbersList = list)
        updateView(isAddNew = true)
        return
      }
      (list.size <= operatorSize) -> {
        list.add(number.toString())
        state = state.copy(numbersList = list)
        updateView(isAddNew = true)
        return
      }
      else -> {
        var lastNumber = list.last()
        when{
          (lastNumber.length >= maxNumber) -> return
          else -> {
            lastNumber += number
            list.removeLast()
            list.add(lastNumber)
            state = state.copy(numbersList = list)
            updateView()
            return
          }
        }
      }
    }
  }

  private fun enterOperator(operator: Operator) {
    when{
      (state.operatorList.isEmpty()) -> {
        val list = state.operatorList
        list.add(operator)
        state = state.copy(operatorList = list)
        updateView(isNumber = false)
      }
      (state.numbersList.isNotEmpty() && state.numbersList.size > state.operatorList.size) -> {
        val list = state.operatorList
        list.add(operator)
        state = state.copy(operatorList = list)
        updateView(isNumber = false)
      }
      else -> return
    }
  }

  private fun addDecimal() {
    val list = state.numbersList
    when{
      (state.numbersList.isEmpty()) -> {
        list.add(".")
        state = state.copy(numbersList = list)
        updateView(isAddNew = true)
        return
      }
      else -> {
        val number = state.numbersList.last() + "."
        list.removeLast()
        list.add(number)
        state = state.copy(numbersList = list)
        updateView()
        return
      }
    }
  }

  private fun calculate() {
    when{
      (state.operatorList.isEmpty() || state.numbersList.isEmpty()) -> return
      (state.operatorList.size >= state.numbersList.size) -> return
      else -> {
        var result : Double? = null
        var size = state.numbersList.size
        for(n in size-1 downTo 1){
          if(result == null){
            var number1 = state.numbersList[n-1].toDouble()
            var number2 = state.numbersList[n].toDouble()
            result = when(state.operatorList[n-1]){
              Operator.Add -> number1 + number2
              Operator.Subtract -> number1 - number2
              Operator.Multiply -> number1 * number2
              Operator.Divide -> number1 / number2
              Operator.None -> 0.0
            }
          }else{
            var number1 = state.numbersList[n-1].toDouble()
            var number2 = result
            result = when(state.operatorList[n-1]){
              Operator.Add -> number1 + number2
              Operator.Subtract -> number1 - number2
              Operator.Multiply -> number1 * number2
              Operator.Divide -> number1 / number2
              Operator.None -> 0.0
            }
          }
        }
        state = InitialDataState()
        var list = state.numbersList
        list.add(result.toString().take(20))
        state = state.copy(numbersList = list)
        updateView(isAddNew = true)
      }
    }
  }

  private fun delete() {
    when {
      (state.numbersList.isEmpty()) -> return
      (state.operatorList.size >= state.numbersList.size) -> {
        val list = state.operatorList
        list.removeLast()
        state = state.copy(operatorList = list)
        updateView(isNumber = false, isDelete = true)
      }
      else -> {
        val index = state.numbersList.lastIndex
        val list = state.numbersList
        when {
          (list[index].isEmpty() || list[index].length <= 1) -> list.removeLast()
          else -> list[index] = list[index].dropLast(1)
        }
        state = state.copy(numbersList = list)
        updateView(isDelete = true)
      }
    }
    return
  }

  private fun updateView(isNumber: Boolean = true, isAddNew: Boolean = false, isDelete: Boolean = false) {
    var display = ""
    when {
      (isDelete) -> {
        display = if(isNumber){
          state.displayValue.dropLast(1)
        }else{
          state.displayValue.dropLast(3)
        }
      }
      else -> {
        when {
         (isNumber) -> {
           display = if(isAddNew){
             state.displayValue + state.numbersList.last()
           }else{
             val displayValue = state.displayValue.dropLast(state.numbersList.last().length - 1)
             displayValue + state.numbersList.last()
           }
         }
         else -> {
           val operator : String = when(state.operatorList.last()) {
             Operator.Add -> " + "
             Operator.Subtract -> " - "
             Operator.Multiply -> " x "
             Operator.Divide -> " / "
             Operator.None -> ""
           }
           display = state.displayValue + operator
         }
        }
      }
    }
    state = state.copy(displayValue = display)
    return
  }
}
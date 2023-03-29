package com.example.calculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CalculatorTheme {
        val viewModel = viewModel<CalculatorViewModel>()
        val state = viewModel.state

        Calculator(
          state = state,
          modifier = Modifier.fillMaxSize().padding(16.dp),
          onClickButton = viewModel::onClickButton,
        )
      }
    }
  }
}

@Composable
fun Calculator(
  state: InitialDataState,
  modifier: Modifier,
  onClickButton : (calculatorType: CalculatorType, number: Int, operator: Operator) -> Unit
) {

  Box(modifier = modifier) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.BottomCenter),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Text(
        text = state.displayValue,
        textAlign = TextAlign.End,
        modifier = Modifier.fillMaxWidth(),
        fontWeight = FontWeight.Light,
        fontSize = 80.sp,
        color = Color.Black,
        maxLines = 2,
      )
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        CalculatorButton(
          symbol = "C",
          color = Color.Red,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(2f)
            .weight(2f),
          onClick = {
            onClickButton(CalculatorType.Clean, 0 , Operator.None)
          },
        )
        CalculatorButton(
          symbol = "D",
          color = Color.Red,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Delete, 0 , Operator.None)
          },
        )
        CalculatorButton(
          symbol = "/",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Operation, 0 , Operator.Divide)
          },
        )
      }
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        CalculatorButton(
          symbol = "7",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Number, 7 , Operator.None)
          },
        )
        CalculatorButton(
          symbol = "8",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Number, 8 , Operator.None)
          },
        )
        CalculatorButton(
          symbol = "9",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Number, 9 , Operator.None)
          },
        )
        CalculatorButton(
          symbol = "X",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Operation, 0 , Operator.Multiply)
          },
        )
      }
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        CalculatorButton(
          symbol = "4",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Number, 4 , Operator.None)
          },
        )
        CalculatorButton(
          symbol = "5",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Number, 5 , Operator.None)
          },
        )
        CalculatorButton(
          symbol = "6",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Number, 6 , Operator.None)
          },
        )
        CalculatorButton(
          symbol = "-",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Operation, 0 , Operator.Subtract)
          },
        )
      }
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        CalculatorButton(
          symbol = "1",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Number, 1 , Operator.None)
          },
        )
        CalculatorButton(
          symbol = "2",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Number, 2 , Operator.None)
          },
        )
        CalculatorButton(
          symbol = "3",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Number, 3 , Operator.None)
          },
        )
        CalculatorButton(
          symbol = "+",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Operation, 0 , Operator.Add)
          },
        )
      }
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        CalculatorButton(
          symbol = "0",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(2f)
            .weight(2f),
          onClick = {
            onClickButton(CalculatorType.Number, 0 , Operator.None)
          },
        )
        CalculatorButton(
          symbol = ".",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.LightGray)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Decimal, 0, Operator.None)
          },
        )
        CalculatorButton(
          symbol = "=",
          color = Color.White,
          textUnit = 18.sp,
          modifier = Modifier
            .background(Color.Yellow)
            .aspectRatio(1f)
            .weight(1f),
          onClick = {
            onClickButton(CalculatorType.Calculate, 0 , Operator.None)
          },
        )
      }
    }
  }
}

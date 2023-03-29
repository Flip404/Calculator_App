package com.example.calculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit

@Composable
fun CalculatorButton(
  symbol: String,
  color: Color,
  textUnit: TextUnit,
  modifier: Modifier,
  onClick: () -> Unit
) {
  TextButton(
    onClick = onClick,
    modifier = Modifier
      .clip(CircleShape)
      .then(modifier)
  ) {
    Text(
      text = symbol,
      color = color,
      fontSize = textUnit
    )
  }
}
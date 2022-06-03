package com.ncorti.kotlin.template.app.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncorti.kotlin.template.library.FactorialCalculator
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

@Suppress("LongMethod")
@Composable
fun Factorial(modifier: Modifier = Modifier) {
    var textFieldState by remember { mutableStateOf("") }
    var factorialResult by remember { mutableStateOf<Long?>(null) }
    var showFactorialError by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "This is just a template",
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "You can compute a factorial using the library-kotlin module.",
            style = MaterialTheme.typography.body1
        )
        TextField(
            value = textFieldState,
            label = { Text(text = "Insert a number to compute the factorial") },
            onValueChange = {
                textFieldState = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("Input"),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (showFactorialError) {
            Text(
                text = "Valid range is 0-20",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.testTag("ErrorMsg")
            )
        }
        AnimatedVisibility(
            visible = factorialResult != null,
            modifier = Modifier
                .padding(top = 8.dp)
                .align(alignment = Alignment.End)
        ) {
            Text(
                text = "$factorialResult",
                style = MaterialTheme.typography.subtitle1.copy(fontSize = 24.sp),
                modifier = Modifier.testTag("FactorialResult")
            )
        }
        Button(
            modifier = Modifier
                .padding(top = 8.dp)
                .align(alignment = Alignment.End),
            onClick = {
                scope.launch {
                    @Suppress("SwallowedException")
                    try {
                        factorialResult =
                            FactorialCalculator.computeFactorial(textFieldState.toLong())
                        showFactorialError = false
                    } catch (ex: IllegalStateException) {
                        showFactorialError = true
                    }
                }
            },
            content = { Text(text = "COMPUTE") }
        )
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun FactorialPreview() {
    MaterialTheme {
        Factorial()
    }
}

package com.example.tip

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tip.ui.theme.TipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipTheme {
                TipLayout(modifier = Modifier.fillMaxSize())

            }
        }
    }
}


@Composable
fun TipLayout(modifier: Modifier){



    var inputAmount by remember{mutableStateOf("")}

    var InputNumeric: Double = inputAmount.toDouble()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
        .fillMaxSize()

    ) {
        Text(stringResource(id = R.string.app_name))
        EditField(
            InputAmount = inputAmount,
            onValueChanged = {inputAmount = it})
        var tip = tipCalculate(billAmount = InputNumeric, tipPercentage = .15)

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = stringResource(id = R.string.tip_amount_format, tip))








    }




}

@Composable
fun EditField(InputAmount: String, onValueChanged: (String)->Unit ){
    TextField(
        value = InputAmount,
        onValueChange = onValueChanged ,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = stringResource(id = R.string.bill_amount_label))
        }
    )

}

@Composable
fun tipCalculate(billAmount: Double, tipPercentage: Double): Double{

    return billAmount * (1 + tipPercentage)


}


@Preview(showBackground = true)
@Composable
fun TipLayoutPreview(){
    TipTheme {
        TipLayout(modifier = Modifier.fillMaxSize())
    }
}

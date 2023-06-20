package com.example.tipcalc

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalc.ui.theme.TipCalcTheme

class MainActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalcApp()
        }
    }
}

@Preview
@Composable
fun TipCalcApp() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFF0))
        ) {
            Calc()
        }
    }
}

@Composable
fun Calc() {
    val bill = remember { mutableStateOf("") }
    val percentage = remember { mutableStateOf("") }
    val numOfCandidates = remember { mutableStateOf("") }
    val totalTip = remember { mutableStateOf("") }
    val distributedAmount = remember { mutableStateOf("") }
    var tipAmount by remember { mutableStateOf(0f) }

    var totalAmount = 0f
    var distributedAmountValue by remember { mutableStateOf(0f) }
    var Split by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Top,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "TipsCalc",
            modifier = Modifier.padding(16.dp),
            fontSize = 70.sp,
            color = androidx.compose.ui.graphics.Color.Black,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.LineThrough,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(39.dp))


        Column(
            modifier = Modifier
                .padding(horizontal = 35.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                //horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedTextField(


                    value= bill.value,
                    onValueChange = { bill.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                        .height(75.dp),



                    shape = RoundedCornerShape(10.dp),
                    placeholder = { Text(text = "Enter your Bill Amount") }
                )
                OutlinedTextField(

                    value= percentage.value,
                    onValueChange = { percentage.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                        .height(75.dp),



                    shape = RoundedCornerShape(10.dp),
                    placeholder = { Text(text = "Enter Your Percentage") }
                )



            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                //horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(


                    value= numOfCandidates.value,
                    onValueChange = { numOfCandidates
                        .value = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                        .height(75.dp),



                    shape = RoundedCornerShape(10.dp),
                    placeholder = { Text(text = "No of People") }
                )

                CalculateButton("Calculate") {val billValue = bill.value.toFloatOrNull()
                    val percentageValue = percentage.value.toFloatOrNull()
                    val candidatesValue = numOfCandidates.value.toIntOrNull()

                    if (billValue != null && percentageValue != null && candidatesValue != null) {
                        if (!percentageValue.equals(0)){
                         tipAmount = (billValue * percentageValue / 100)
                         totalAmount = (billValue + tipAmount)
                         distributedAmountValue = tipAmount/ candidatesValue

                        totalTip.value = "Total Tip: $tipAmount"
                        distributedAmount.value = "Split Tip: $distributedAmountValue"}
                        else{
                             tipAmount = billValue
                             totalAmount = (billValue + tipAmount)
                             distributedAmountValue = tipAmount/ candidatesValue


                        }
                    }
                    Split= billValue!! / candidatesValue!!

                    }
                }
            }
        Spacer(modifier = Modifier.height(15.dp))

            Column(modifier = Modifier.fillMaxWidth(),
                Arrangement.Bottom,
                horizontalAlignment = Alignment.Start) {



            if (totalTip.value.isNotEmpty()) {
                Text(
                    text ="Total Tip",


                    modifier = Modifier.padding(16.dp),
                    fontSize = 30.sp,
                    color = Color(0xFF8B5A2B),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Center,

                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = "  $tipAmount",

                    modifier = Modifier
                        .size(400.dp,75.dp)
                        .background(color = androidx.compose.ui.graphics.Color.LightGray),
                    fontSize = 30.sp,
                    color = androidx.compose.ui.graphics.Color.White
                )

            }
            if (distributedAmount.value.isNotEmpty()) {
                Text(
                    text = "Split Tip",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 30.sp,
                    color = Color(0xFF8B5A2B),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Center,

                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = "  $distributedAmountValue",

                    modifier = Modifier
                        .size(400.dp,75.dp)
                        .background(color = androidx.compose.ui.graphics.Color.LightGray),
                    fontSize = 30.sp,
                    color = androidx.compose.ui.graphics.Color.White
                )

            }
                if(!Split.equals(0f)){


                Text(
                    text = "Individual Share",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 30.sp,
                    color = Color(0xFF8B5A2B),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Center,

                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = "  $Split",

                    modifier = Modifier
                        .size(400.dp,75.dp)
                        .background(color = androidx.compose.ui.graphics.Color.LightGray),
                    fontSize = 30.sp,
                    color = androidx.compose.ui.graphics.Color.White
                )}}
        }}




@Composable
fun CalculateButton(buttonText: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(start = 8.dp)
            .height(75.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        Text(text = buttonText)
    }
}


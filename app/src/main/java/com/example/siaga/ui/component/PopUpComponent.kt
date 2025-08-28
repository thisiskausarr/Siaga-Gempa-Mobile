package com.example.siaga.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PopUpComponent(title:String,description:String,onSuccess:()->Unit ,onCanceled:()->Unit){
    Card() {
        Column(horizontalAlignment = Alignment.CenterHorizontally,modifier=Modifier.padding(16.dp)) {

            Text(text=title, style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold

            ),
                modifier=Modifier.padding(bottom = 16.dp)
                )
            Text(text = description,style=TextStyle(
                fontSize=16.sp,
                textAlign = TextAlign.Center
            ),
            color = Color.Gray
            )
            Column (){
                if(onCanceled !==null){
                    OutlinedButtonComponent(borderColor = Color.Gray, OnClick = onCanceled ){
                        Text(text = "Cancel", color = Color.Red, style = TextStyle(
                            fontWeight = FontWeight.ExtraBold
                        )
                        )
                    }
                }
                if(onSuccess!== null){
                    ButtonComponent(label = "Selesai", OnClick = onSuccess )
                }
            }
        }

    }
}

@Preview
@Composable
fun Preview(){
    PopUpComponent(
        title = "Laporan Selesai",
        description = "Terimakasih atas laporan anda. Tetap waspada terhadap gempa susulan  dan pastikan keamanan anda.",
        onSuccess = { /*TODO*/ }, onCanceled = {

        })
}
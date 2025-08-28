package com.example.siaga.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun ButtonComponent (label:String, modifier:Modifier=Modifier, color :Color = Color(2, 125, 180, 228),
                     OnClick:   ()->Unit,
                     content: @Composable() (RowScope.() -> Unit)? =null,
                     ){
    Button(
        onClick =OnClick ,
        colors = ButtonDefaults.buttonColors(containerColor =color ),
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
    ) {
        Row (content= {
            if (content ===null) Text(text = label ?: "")
            else content()
        })
    }
}
@Composable
fun OutlinedButtonComponent(
    label:String?=null, OnClick:()->Unit,
    borderColor:Color? = Color.White,
    bgColor: Color? = Color.Transparent,
    modifier:Modifier=Modifier,
    content: @Composable() (RowScope.() -> Unit)? =null,

){
    Button(
        onClick =OnClick ,
        colors = ButtonDefaults.buttonColors(containerColor =bgColor ?:Color.White),
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, borderColor ?: Color.Transparent, RoundedCornerShape(20.dp)),
    ){

    Row (content= {
    if (content ===null) Text(text = label ?: "")
    else content()
})
    }

}
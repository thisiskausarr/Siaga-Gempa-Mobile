package com.example.siaga.ui.component

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.siaga.R

@Composable
fun OutlinedTextFieldComponent(

    label:String,
    type:String="Text",
    value: String,
    modifier: Modifier = Modifier,
    keyboardOptions:KeyboardOptions? = null,

    OnChange:(String)->Unit,

){
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    Log.d("password0.",passwordVisible.toString())
    OutlinedTextField(
        value = value,
        colors = OutlinedTextFieldDefaults.colors(

            unfocusedBorderColor = Color(2, 125, 180, 228)
        ),
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(4.dp)),
        singleLine = true,
        shape = RoundedCornerShape(20),
        visualTransformation = if (!passwordVisible && type=="Password")  PasswordVisualTransformation()  else VisualTransformation.None,

        onValueChange = {it->
                        OnChange(it.toString())
        },
        label={ Text(text = label)},
        keyboardActions = KeyboardActions.Default,
        keyboardOptions = keyboardOptions ?: KeyboardOptions.Default,
        trailingIcon = {
            if (type =="Password"){

            val image = if (passwordVisible)
                Icons.Filled.Lock
            else Icons.Filled.Face

            // Please provide localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)

            }
            }
            if (type == "Search"){
            Icon( painter = painterResource(id = R.drawable.search), modifier=modifier.size(20.dp), contentDescription ="")
            }

        }

    )
}
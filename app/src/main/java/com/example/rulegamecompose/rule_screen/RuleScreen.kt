package com.example.rulegamecompose.rule_screen




import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rulegamecompose.R
import com.example.rulegamecompose.ui.theme.Reed
import kotlin.math.roundToInt


@Composable
fun RuleScreen() {

    var rotationValue by remember {
        mutableStateOf(0f)
    }

    var numberIndex by remember {
        mutableStateOf(0)
    }
    var number by remember {
        mutableStateOf(0)
    }
    val angle : Float by animateFloatAsState(
        targetValue = rotationValue ,
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearOutSlowInEasing
        )
    )

    Column(modifier = Modifier.fillMaxSize().padding(top = 10.dp) ,
        verticalArrangement = Arrangement.SpaceBetween){
        Text(text = "$number" ,
            fontWeight = FontWeight.Bold ,
            fontSize = 35.sp ,
            color = Color.White,
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp) ,
            textAlign = TextAlign.Center
        )

        Box(modifier = Modifier.weight(1f).fillMaxSize()){

            Image(painter = painterResource(R.drawable.ruleta) , contentDescription = "Rulet" ,
                modifier = Modifier.fillMaxSize()
                    .rotate(angle))
            Image(painter = painterResource(R.drawable.flecha) , contentDescription = "Flecha" ,
                modifier = Modifier.fillMaxSize())
        }
        Button(onClick = {
            rotationValue = (720..1080).random().toFloat() + angle
            numberIndex = calculation(rotationValue)
            Log.d("answer" , rotationValue.toString())
            number = ValueList.list1[numberIndex]

        } ,
            colors = ButtonDefaults.buttonColors(
                containerColor = Reed
            ) ,
            modifier = Modifier.fillMaxWidth().padding(10.dp)){
            Text(
                text = "Start" ,
                color = Color.White
            )
        }

    }
}

fun calculation(rotationValue : Float) : Int{

    var sector : Float = 360f / 37f
    var rValue = rotationValue
    var sum = rValue % 360
    var answer = 0f

    answer = (sum / sector)
    Log.d("answer" , "answer = $answer")
    return answer.roundToInt()

}
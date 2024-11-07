package com.example.navahgation1.screens

import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.example.navahgation1.R
@Composable
fun  StartOrderScreen(
    quintityOptions:List<Pair<Int,Int>>,
    onNestButtonClicked:(Int)->Unit,
    modifier: Modifier=Modifier
){
    Column(
        modifier=modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Image(
                painter = painterResource(id =R.drawable.check5 ),
                contentDescription =null,
                modifier=Modifier
                    .width(300.dp)

            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.order_cupcakes),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(12.dp))


            Column (
                modifier=Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                       quintityOptions.forEach{item->
                           SelectQuantityButton(
                               labelResource = item.first,
                               onCluick = {onNestButtonClicked(item.second)},
                               modifier=Modifier.fillMaxWidth()
                           )
                       }
            }
        }
    }
}




@Composable
fun  SelectQuantityButton(
    @StringRes labelResource:Int,
    onCluick:()->Unit,
    modifier: Modifier=Modifier

){
    Button(
        onClick = onCluick,
        modifier=modifier.widthIn(min=240.dp)
    ) {
Text(
    text = stringResource(labelResource)
)
    }
}


private  fun startss(){
    val intent=Intent.ACTION_SCREEN_OFF


}



package com.example.navahgation1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navahgation1.screens.component.FormattedPriceLable
import  com.example.navahgation1.R
@Composable
fun SelectOptionScreen(
    Subtotal:String,
    options:List<String>,
    onSlelectSchanged:(String)-> Unit={},
    onCancleButtionClicked:()-> Unit={},
    onNextButtionCliked:()->Unit={},
    modifier: Modifier=Modifier
){
    var seleCedValues by rememberSaveable { mutableStateOf("") }
    Column(
        modifier=modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier=Modifier.padding(12.dp)){
            options.forEach{ item->
                Row (
                    modifier=Modifier.selectable(
                        selected = seleCedValues==item,
                        onClick = {
                            seleCedValues=item
                            onSlelectSchanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    RadioButton(
                        selected =seleCedValues==item,
                        onClick = {
                            seleCedValues=item
                            onSlelectSchanged(item)
                        }
                    )

                    Text(item)
                }
            }
            Divider(
                thickness = 5.dp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        top = 12.dp,
                        bottom = 12.dp
                    )
            )

            FormattedPriceLable(
                subtotole = Subtotal,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        top = 12.dp,
                        bottom = 12.dp
                    )
                )
        }

        Row (
            modifier= Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Bottom
        ){

            OutlinedButton(
                onClick = onCancleButtionClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
            Button(
                onClick = onNextButtionCliked,
                enabled = seleCedValues.isEmpty(),
                modifier = Modifier.weight(1f)
                ) {
                Text(text = stringResource(id = R.string.next))
            }
        }

    }
}





@Preview
@Composable
fun  Seeall(){
    Scaffold {innerpadding->
        SelectOptionScreen(Subtotal = "1212", options = listOf("aman 1","kaka  2","dada 3"), modifier = Modifier.padding(innerpadding))
    }
}
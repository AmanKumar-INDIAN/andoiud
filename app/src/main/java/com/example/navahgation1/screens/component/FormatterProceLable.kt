package com.example.navahgation1.screens.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.navahgation1.R
@Composable
fun FormattedPriceLable(subtotole:String,modifier: Modifier=Modifier){
    Text(
        text = stringResource(id = R.string.subtotal_price),
        modifier=modifier,
        style = MaterialTheme.typography.headlineSmall
        )
}
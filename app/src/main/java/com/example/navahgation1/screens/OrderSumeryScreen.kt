package com.example.navahgation1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.navahgation1.data.ObjectuiState
import com.example.navahgation1.R
import com.example.navahgation1.screens.component.FormattedPriceLable

@Composable
fun  OrderSummeryScreenScreen(
    orderuistate:ObjectuiState,
    onchancleButtonClick:()->Unit,
    onSemdButtonClicked:(String,String)->Unit,
    modifier: Modifier=Modifier
){
val resources= LocalContext.current.resources
    val numberofCupCakes=resources.getQuantityString(
        R.plurals.cupcakes,
        orderuistate.quniity,
        orderuistate.quniity
    )
    //Load and format a string resource with the parameters.
    val orderSummary = stringResource(
        R.string.order_details,
        numberofCupCakes,
      orderuistate.flavors,
        orderuistate.data,
        orderuistate.quniity
    )
    val newOrder= stringResource(id = R.string.new_cupcake_order)
    val items= listOf(
        Pair(stringResource(R.string.quantity), numberofCupCakes),
        // Summary line 2: display selected flavor
        Pair(stringResource(R.string.flavor), orderuistate.flavors),
        // Summary line 3: display selected pickup date
        Pair(stringResource(R.string.pickup_date), orderuistate.data)
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items.forEach { item ->
                Text(item.first.uppercase())
                Text(text = item.second, fontWeight = FontWeight.Bold)
                Divider(thickness = 5.dp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            FormattedPriceLable(
                subtotole = orderuistate.price,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSemdButtonClicked(newOrder, orderSummary) }
                ) {
                    Text(stringResource(R.string.send))
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onchancleButtonClick
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        }
    }
}
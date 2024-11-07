package com.example.navahgation1.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.navahgation1.data.ObjectuiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private  const val  Price_Per_CupCake=2.00
private  const val  Price_for_some_day=3.00


class OrderViewModel:ViewModel(){
    private  val  _Uistate= MutableStateFlow(ObjectuiState())
    val uiState=_Uistate.asStateFlow()

    fun setQuilty(numberCupCake:Int){
     _Uistate.update {currentstate->
         currentstate.copy(
             quniity = numberCupCake,
             price = CalculatePrice(quintity = numberCupCake)
         )
     }
    }

    fun  setFlovcer(desiredFlover:String){
        _Uistate.update {currewnyt->
            currewnyt.copy(
                flavors = desiredFlover
            )
        }
    }

    fun setDate(PickupDate:String){
        _Uistate.update { currentval->
            currentval.copy(
                data = PickupDate,
                price = CalculatePrice(pickupdate = PickupDate)
            )
        }
    }
    fun  ResertOrder(){
        _Uistate.value=ObjectuiState(pickUpOptions = pickUpOptions())
    }


    private  fun CalculatePrice(
        quintity:Int = _Uistate.value.quniity,
        pickupdate:String = _Uistate.value.data
    ):String{
        var calculatePrice = quintity * Price_Per_CupCake
       if (pickUpOptions()[0]==pickupdate){
           calculatePrice += Price_for_some_day
       }
        val formattedPrice= NumberFormat.getCurrencyInstance().format(calculatePrice)
        return  formattedPrice

    }



    private fun  pickUpOptions():List<String>{
        val dateOptions= mutableListOf<String>()
        val formatter= SimpleDateFormat("E MMM d", Locale.getDefault())
        val calamnder= Calendar.getInstance()
        repeat (4){
            dateOptions.add(formatter.format(calamnder.time))
            calamnder.add(Calendar.DATE,1)
        }
        return  dateOptions
    }
}




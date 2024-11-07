package com.example.navahgation1

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.navahgation1.screens.OrderViewModel
import  androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navahgation1.data.DataSource
import com.example.navahgation1.screens.OrderSummeryScreenScreen
import com.example.navahgation1.screens.StartOrderScreen
import com.example.navahgation1.screens.SelectOptionScreen

enum class CupCakeScreen ( @StringRes val  title : Int){
    Start(title = R.string.app_name),
    Flavore(title = R.string.choose_flavor),
    PickUp(title = R.string.choose_pickup_date),
    Summery(title = R.string.order_summary)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeAppbar(
    CurrentScreen:CupCakeScreen,
    canNAvigateBack:Boolean,
    navigateUp:()->Unit,
    modifier: Modifier=Modifier
){
TopAppBar(
    title = { Text(text = stringResource(CurrentScreen.title))}

)
}



@Composable
fun  CupcakeApp(
viewModel: OrderViewModel = viewModel(),
navController: NavHostController  = rememberNavController()
){
    val  backStackEntry by navController.currentBackStackEntryAsState()
    val  currentScreen=CupCakeScreen.valueOf(
        backStackEntry?.destination?.route ?: CupCakeScreen.Start.name
    )

    Scaffold (
        topBar = {
            CupcakeAppbar(
                CurrentScreen = currentScreen,
                canNAvigateBack = navController.previousBackStackEntry!=null,
                 navigateUp = { navController.navigateUp()}
            )
        }
    ){ innerpaddding ->
        val  uiState by viewModel.uiState.collectAsState()
 NavHost(
     navController = navController,
     startDestination = CupCakeScreen.Start.name,
     modifier= Modifier
         .fillMaxSize()
         .verticalScroll(rememberScrollState())
         .padding(innerpaddding)
 ) {
     composable(route = CupCakeScreen.Start.name){
       StartOrderScreen(
           quintityOptions = DataSource.QunitityOptions,
           onNestButtonClicked ={
               viewModel.setQuilty(it)
               navController.navigate(CupCakeScreen.Flavore.name)
           },
           modifier = Modifier
               .fillMaxSize()
               .padding(12.dp)
       )
     }
     composable(route = CupCakeScreen.Flavore.name){
         val contex= LocalContext.current
         SelectOptionScreen(
             Subtotal =uiState.price ,
             options =DataSource.falvores.map { id->contex.resources.getString(id) },
             onNextButtionCliked = {navController.navigate(CupCakeScreen.PickUp.name)},
             onCancleButtionClicked = {
                 cencelOrderNavigateTostart(viewModel,navController)
             },
             onSlelectSchanged = {viewModel.setFlovcer(it)},
             modifier = Modifier.fillMaxHeight()
         )
     }
     composable(CupCakeScreen.PickUp.name){
         SelectOptionScreen(
             Subtotal =uiState.price ,
             options =uiState.pickUpOptions,
             onNextButtionCliked = {navController.navigate(CupCakeScreen.Summery.name)},
             onSlelectSchanged = {viewModel.setDate(it)},
             onCancleButtionClicked = {
                 cencelOrderNavigateTostart(viewModel,navController)
             },
             modifier = Modifier.fillMaxHeight()
         )
     }
     composable(CupCakeScreen.Summery.name){
         val contex= LocalContext.current
         OrderSummeryScreenScreen(
             orderuistate = uiState,
             onchancleButtonClick = {
                 cencelOrderNavigateTostart(viewModel,navController)
             },
             onSemdButtonClicked = {subject:String,summery:String->
                 Shareorder(contex,  subject,summery)
             },
             modifier = Modifier.fillMaxHeight()
         )
     }

    }

Text(text = ":hello amamns")
}
}





private  fun cencelOrderNavigateTostart(
    viewModel: OrderViewModel,
    navController: NavHostController
){
    viewModel.ResertOrder()
    navController.popBackStack(CupCakeScreen.Start.name, inclusive = false)
}



private  fun Shareorder(context:Context,subject:String,summmery:String){
    val  intent =Intent(Intent.ACTION_SEND).apply {
        type="text/plain"
        putExtra(Intent.EXTRA_SUBJECT,subject)
        putExtra(Intent.EXTRA_TEXT,summmery)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_cupcake_order)
        )
    )
}




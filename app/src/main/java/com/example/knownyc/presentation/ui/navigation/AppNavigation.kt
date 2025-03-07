package com.example.knownyc.presentation.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.knownyc.R
import com.example.knownyc.presentation.boroughs.BoroughsScreen
import com.example.knownyc.presentation.parks.NycParksScreenParent

import com.example.knownyc.presentation.ui.util.scaffold.AppScaffold
import com.example.knownyc.presentation.ui.util.scaffold.TitleText

@Composable
fun AppNavigationGraph(){
    val navController = rememberNavController()
    val isBackEnabled = remember {
        mutableStateOf(false)
    }

    val title = remember {
        mutableIntStateOf(R.string.screen_title_home)
    }

    // titleArgs: used by stringResource() to get Scaffold topBar title
    val titleArgs = remember {
        mutableStateOf("")
    }

    val showSearchIcon = remember {
        mutableStateOf(false)
    }

    val searchClicked = remember {
        mutableStateOf(false)
    }

    val searchText = remember {
        mutableStateOf("")
    }

    navController.addOnDestinationChangedListener{_, destination, _ ->
        isBackEnabled.value = !destination.route.equals(Routes.HOME_SCREEN)

        if(destination.route!!.startsWith(Routes.PARKS_SCREEN,0)){
            title.intValue = R.string.screen_title_parks
            showSearchIcon.value = true
        }
        else{
            title.intValue = R.string.screen_title_home
            showSearchIcon.value = false
            searchClicked.value = false
        }
    }

    AppScaffold(
        title = {
            if(searchClicked.value){
                //TODO: Project 2: Extra Credit
//                SearchTextField(placeholder = stringResource(id = R.string.search_parks_placeholder))
//                searchText.value = value
            }

            else{
                TitleText(title = stringResource(id = title.intValue, titleArgs.value))
        }

    },
        showBackIcon = isBackEnabled.value,
        onBackClick = {
            if(!searchClicked.value) navController.popBackStack()
            searchClicked.value = !searchClicked.value
        },
        showSearchIcon = showSearchIcon.value && !searchClicked.value,
        onActionClick ={
            searchClicked.value = true
        }
    ) {paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {
                composable(Routes.HOME_SCREEN){
                    BoroughsScreen(onBoroughClicked = { borough, title ->
                        titleArgs.value = title
                        navController.navigate("${Routes.PARKS_SCREEN}/$borough")
                    })
                }

                composable(
                    "${Routes.PARKS_SCREEN}/{borough}",
                    arguments = listOf(
                        navArgument("borough"){
                            type = NavType.StringType
                        },
                    ),
                )
                {
                    backStackEntry ->
                    //TODO: Project 2
                    NycParksScreenParent(
                        searchText = searchText.value,
                    )
                }
            }
        }

    }

}
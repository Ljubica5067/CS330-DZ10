package rs.ac.metropolitan.cs330_dz10

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import rs.ac.metropolitan.cs330_dz10.screens.AppViewModel
import rs.ac.metropolitan.cs330_dz10.screens.HomeScreen
import rs.ac.metropolitan.cs330_dz10.screens.PosiljkaDetailScreen

@Composable
fun NavSettup(navController: NavHostController, paddingValues: PaddingValues){
    val vm: AppViewModel = viewModel()
    vm.navController = navController

    NavHost(navController = navController, startDestination = NavigationRoutes.Home.route){
        composable(route = NavigationRoutes.Home.route) {
            HomeScreen(vm, paddingValues)
        }
        composable(route = NavigationRoutes.PosiljkaDetailScreen.route){ navBackStackEntry ->
            val elementId = navBackStackEntry.arguments?.getString("elementId")
            if (elementId != null) {
                PosiljkaDetailScreen(vm = vm, id = elementId, paddingValues = paddingValues)
            } else {
                Toast.makeText(
                    navController.context,
                    "Error, elementId is required!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

}
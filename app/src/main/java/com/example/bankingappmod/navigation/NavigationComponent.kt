package com.example.bankingappmod.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bankingappmod.data.TransactionItemData
import com.example.bankingappmod.screens.AccountScreen
import com.example.bankingappmod.screens.AddTransactionScreen
import com.example.bankingappmod.screens.AllTransactionsScreen
import com.example.bankingappmod.screens.DetailsTransactionScreen
import com.example.bankingappmod.screens.FilterScreen
import com.example.bankingappmod.screens.SelectAccountScreen
import com.example.bankingappmod.ui.theme.accountScreen
import com.example.bankingappmod.ui.theme.addTransactionScreen
import com.example.bankingappmod.ui.theme.allTransactionsScreen
import com.example.bankingappmod.ui.theme.transactionDetailScreen
import com.example.bankingappmod.utils.TransactionStatus
import com.example.bankingappmod.utils.dateFormatter
import com.example.bankingappmod.vm.TransactionsViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NavigationComponent(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var shouldShowSelectAccountSheet by remember { mutableStateOf(false) }
    var shouldShowFilterScreen by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            if (shouldShowSelectAccountSheet) {
                SelectAccountScreen(
                    onSelAccountClick = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                            shouldShowSelectAccountSheet = false
                        }
                    },
                    onDismiss = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                            shouldShowSelectAccountSheet = false
                        }
                    }
                )
            }
        },
        content = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                content = {
                    if (shouldShowFilterScreen) {
                        FilterScreen(
                            onSubmitClick = {
                                shouldShowFilterScreen = false
                            }
                        )
                    }
                    NavHost(navController = navController, startDestination = accountScreen) {
                        composable(accountScreen) {
                            AccountScreen(
                                onSelectAccountClick = {
                                    shouldShowSelectAccountSheet = true
                                    coroutineScope.launch {
                                        bottomSheetState.show()
                                    }
                                },
                                onViewAllClick = {
                                    navController.navigate(allTransactionsScreen)
                                },
                                onTransactionClick = { transactionId ->
                                    navController.navigate("$transactionDetailScreen/$transactionId")
                                },
                                onAddClick = {
                                    navController.navigate(addTransactionScreen)
                                }
                            )
                        }
                        composable(addTransactionScreen) {
                            AddTransactionScreen(
                                onOkayClick = {
                                    navController.navigate(accountScreen)
                                }
                            )
                        }
                        composable(allTransactionsScreen) {
                            AllTransactionsScreen(
                                onTransactionClick = { transactionId ->
                                    navController.navigate("$transactionDetailScreen/$transactionId")
                                },
                                onBackClick = {
                                    navController.navigate(accountScreen)
                                },
                                onFilterClick = {
                                    shouldShowFilterScreen = true
                                }
                            )
                        }
                        composable(
                            route = "$transactionDetailScreen/{transactionId}",
                            arguments = listOf(navArgument("transactionId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val transactionId = backStackEntry.arguments?.getInt("transactionId") ?: 0
                            DetailsTransactionScreen(
                                transactionId = transactionId,
                                onOkayClick = {
                                    navController.navigate(accountScreen)
                                }
                            )
                        }
                    }
                }
            )
        }
    )
}
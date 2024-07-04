package com.example.bankingappmod

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankingappmod.data.TransactionItemData
import com.example.bankingappmod.screens.AccountScreen
import com.example.bankingappmod.screens.AddTransactionScreen
import com.example.bankingappmod.screens.AllTransactionsScreen
import com.example.bankingappmod.screens.DetailsTransactionScreen
import com.example.bankingappmod.screens.FilterScreen
import com.example.bankingappmod.screens.SelectAccountScreen
import com.example.bankingappmod.ui.theme.BankingAppModTheme
import com.example.bankingappmod.ui.theme.accountScreen
import com.example.bankingappmod.ui.theme.addTransactionScreen
import com.example.bankingappmod.ui.theme.allTransactionsScreen
import com.example.bankingappmod.ui.theme.transactionDetailScreen
import com.example.bankingappmod.utils.TransactionStatus
import com.example.bankingappmod.utils.dateFormatter
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            BankingAppModTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationComponent(navController = navController)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
    @Composable
    fun NavigationComponent(navController: NavHostController) {
        val coroutineScope = rememberCoroutineScope()
        val bottomSheetState =
            rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
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
                                },
                                onCalendarIconClick = {

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
                                    onTransactionClick = {
                                        navController.navigate(transactionDetailScreen)
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
                                    onTransactionClick = {

                                    },
                                    onBackClick = {
                                        navController.navigate(accountScreen)
                                    },
                                    onFilterClick = {
                                        shouldShowFilterScreen = true
                                    }
                                )
                            }
                            composable(transactionDetailScreen) {
                                DetailsTransactionScreen(
                                    transactionItemData = TransactionItemData(
                                        "11",
                                        "11",
                                        dateFormatter(),
                                        TransactionStatus.EXECUTED,
                                        10.02f
                                    ),
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
}
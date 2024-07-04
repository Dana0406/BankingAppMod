package com.example.bankingappmod

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankingappmod.data.AccountItemData
import com.example.bankingappmod.data.TransactionItemData
import com.example.bankingappmod.items.AccountItem
import com.example.bankingappmod.items.TransactionItem
import com.example.bankingappmod.screens.AccountScreen
import com.example.bankingappmod.screens.AddTransactionScreen
import com.example.bankingappmod.screens.AllTransactionsScreen
import com.example.bankingappmod.screens.DetailsTransactionScreen
import com.example.bankingappmod.ui.theme.BankingAppModTheme
import com.example.bankingappmod.ui.theme.accountScreen
import com.example.bankingappmod.ui.theme.addTransactionScreen
import com.example.bankingappmod.ui.theme.allTransactionsScreen
import com.example.bankingappmod.ui.theme.transactionDetailScreen
import com.example.bankingappmod.utils.TransactionStatus
import com.example.bankingappmod.utils.dateFormatter
import java.util.Date

class MainActivity : ComponentActivity() {
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
                    Scaffold(
                        content = {
                            NavigationComponent(navController = navController)
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun NavigationComponent(navController: NavHostController) {
        NavHost(navController = navController, startDestination = accountScreen) {
            composable(accountScreen) {
                AccountScreen(
                    onSelectAccountClick = {

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

                    }
                )
            }
            composable(transactionDetailScreen) {
                DetailsTransactionScreen(
                    transactionItemData = TransactionItemData("11", "11", dateFormatter(), TransactionStatus.EXECUTED, 10.02f),
                    onOkayClick = {
                        navController.navigate(accountScreen)
                    }
                )
            }
        }
    }

}

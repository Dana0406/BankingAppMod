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
import com.example.bankingappmod.ui.theme.BankingAppModTheme
import com.example.bankingappmod.utils.TransactionStatus
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
                ) { val navController = rememberNavController()
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
        NavHost(navController = navController, startDestination = "account_screen") {
            composable("account_screen") {
                AccountScreen(
                    onSelectAccountClick = {  },
                    onViewAllClick = {  },
                    onTransactionClick = {  }
                ) {

                }
            }
        }
    }

}


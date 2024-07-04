package com.example.bankingappmod.utils
fun maskCardNumber(cardNumber: String): String {
    return if (cardNumber.length > 4) {
        "•••• ${cardNumber.takeLast(4)}"
    } else {
        cardNumber
    }
}
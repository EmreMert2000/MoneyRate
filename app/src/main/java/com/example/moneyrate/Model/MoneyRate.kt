package com.example.moneyrate.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoneyRate(
    //@SerializedName("currency")
    val currency:String,
    //@SerializedName("price")
    val price:String

) {

}
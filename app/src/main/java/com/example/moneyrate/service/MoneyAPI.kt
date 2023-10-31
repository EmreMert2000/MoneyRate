package com.example.moneyrate.service

import android.database.Observable
import com.example.moneyrate.Model.MoneyRate
import retrofit2.http.GET

interface MoneyAPI {


    //https://github.com/atilsamancioglu/K21-JSONDataSet/blob/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json") //Example API
    fun getData(): Observable<List<MoneyRate>>
}
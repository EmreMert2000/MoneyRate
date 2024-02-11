package com.example.moneyrate.service

import android.database.Observable
import com.example.moneyrate.Model.MoneyRate
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface MoneyAPI {


    //https://github.com/atilsamancioglu/K21-JSONDataSet/blob/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json") //Example API

    fun getData(): Single<Response<List<MoneyRate>>>
}
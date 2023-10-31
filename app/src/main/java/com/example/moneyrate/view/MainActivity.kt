package com.example.moneyrate.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyrate.Model.MoneyRate
import com.example.moneyrate.adapters.RecAdapter
import com.example.moneyrate.databinding.ActivityMainBinding
import com.example.moneyrate.service.MoneyAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels: ArrayList<MoneyRate>? = null
    private var recyclerViewAdapter : RecAdapter? = null

    //Disposable
   // private var compositeDisposable: CompositeDisposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       // val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
       // binding.recyclerView.layoutManager = layoutManager

           loaddata()

    }
    fun loaddata()
    {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(MoneyAPI::class.java)




    }

}
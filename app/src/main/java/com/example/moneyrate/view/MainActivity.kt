package com.example.moneyrate.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.database.Observable
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyrate.Model.MoneyRate
import com.example.moneyrate.adapters.RecAdapter
import com.example.moneyrate.databinding.ActivityMainBinding
import com.example.moneyrate.service.MoneyAPI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var moneyModels: ArrayList<MoneyRate>? = null
    private var recyclerViewAdapter : RecAdapter? = null

    private lateinit var compositeDisposable: CompositeDisposable


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        compositeDisposable = CompositeDisposable()

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

           loaddata()

    }
    fun loaddata()
    {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(MoneyAPI::class.java)

        val disposable = retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response: Response<List<MoneyRate>> ->
                if (response.isSuccessful) {
                    val moneyList: List<MoneyRate>? = response.body()
                    handleResponse(moneyList!!)
                } else {
                    Log.e("MainActivity", "API request failed: ${response.code()}, ${response.message()}")
                }
            }, { t: Throwable ->
                Log.e("MainActivity", "API request failed: ${t.message}", t)
            })

        compositeDisposable.add(disposable)
        






    }


    private fun handleResponse(MoneyList : List<MoneyRate>){
        moneyModels = ArrayList(MoneyList)

        moneyModels?.let {
            recyclerViewAdapter = RecAdapter(it,this@MainActivity)
            binding.recyclerView.adapter = recyclerViewAdapter
        }
    }



    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }

    fun onItemClick(moneyRate: MoneyRate) {
        Toast.makeText(this,"Clicked : ${moneyRate.currency}",Toast.LENGTH_LONG ).show()
    }

}





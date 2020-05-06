package com.example.newkorona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

import android.util.Log
import android.util.Log.d
import android.widget.TextView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL
import com.google.gson.Gson
import kotlinx.android.synthetic.main.data_row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://coronavirus-19-api.herokuapp.com/countries/?fbclid=IwAR0wZuXrAzDdY6q8rTQGLmun_-l6ZLVv6MLP8h67JC3Q2aHf8mVPRNpyNpU")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.fetchAllCountries().enqueue(object : Callback<List<Country>>{
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                d("TAG_KORONA","error" + t.message)
            }
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                showData(response.body()!!)
                d("TAG_KORONA","ok")
                d("TAG_KORONA", "List  size: " + response.body()!![2].country)
            }
        })

//        val countries = mutableListOf<Country>()
//        for (i in 0..20){
//            countries.add(Country("Freljord",
//                12343,
//                234,
//                6,
//                4,
//                22,
//                23,
//                23,
//                234,
//                234,
//                5,
//                4))
//        }

//        recyclerView.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = MainAdapter(countries) // maybe
//        }
    }
    private fun showData(countries: List<Country>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(countries) // maybe
        }
//        val country:TextView = findViewById(R.id.country_view) as TextView
//        val cases:TextView = findViewById(R.id.cases_view) as TextView
//
//        searchView.setSearchableInfo(countries)
    }
}

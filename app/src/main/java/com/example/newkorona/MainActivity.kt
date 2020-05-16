package com.example.newkorona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

import android.util.Log.d
import android.widget.EditText
import com.example.newkorona.filter.CountriesListFilter
import com.example.newkorona.filter.CountriesListFilterImpl
import com.example.newkorona.filter.DeathCountriesListFilter
import com.example.newkorona.repository.CountriesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val mainAdapter = MainAdapter(emptyList())
    private val countryListFilter: CountriesListFilter = CountriesListFilterImpl()
    private var countryList: List<Country> = emptyList()

    private var countryRepository: CountriesRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText:EditText = findViewById(R.id.editText)

        editText.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val filteredList = countryListFilter.filter(countryList, s.toString())
                showData(filteredList)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        })

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }

        countryRepository?.fetchAllCountries { showData(countryList) }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://coronavirus-19-api.herokuapp.com/countries/?fbclid=IwAR0wZuXrAzDdY6q8rTQGLmun_-l6ZLVv6MLP8h67JC3Q2aHf8mVPRNpyNpU")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)


        api.fetchAllCountries().enqueue(object : Callback<List<Country>> {
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                d("TAG_KORONA", "error" + t.message)
            }

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                response.body()?.let { newCountryList -> countryList = newCountryList }
                showData(countryList)
                d("TAG_KORONA", "ok")
                d("TAG_KORONA", "List  size: " + response.body()!![2].country)

            }
        })
    }

    private fun showData(countries: List<Country>) {
            mainAdapter.updateCountriesList(countries)
    }




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
//    private fun showData(countries: List<Country>) {
//        recyclerView.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = MainAdapter(countries) // maybe
//        }
//    }
}

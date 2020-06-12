package com.example.newkorona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.EditText
import com.example.newkorona.filter.CountriesListFilter
import com.example.newkorona.filter.CountriesListFilterImpl
import com.example.newkorona.repository.CountriesRepository
import com.example.newkorona.repository.CountriesRepositoryImpl


class MainActivity : AppCompatActivity() {

    private val mainAdapter  = MainAdapter(emptyList())
    private val countryListFilter: CountriesListFilter = CountriesListFilterImpl()
    private var countryList: List<Country> = emptyList()
    private val countryRepository : CountriesRepository? = CountriesRepositoryImpl()


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
            layoutManager = LinearLayoutManager( this@MainActivity )
            adapter = mainAdapter
        }

        countryRepository?.fetchAllCountries {
            countryList = it
            showData(countryList) }
   }


    private fun showData(countries: List<Country>) {
            mainAdapter.updateCountriesList(countries)
    }
}


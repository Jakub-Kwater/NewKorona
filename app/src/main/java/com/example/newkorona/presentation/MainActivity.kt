package com.example.newkorona.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.newkorona.*
import com.example.newkorona.filter.CountriesListFilterImpl
import com.example.newkorona.repository.CountriesRepositoryImpl
import com.example.newkorona.roomDataBase.dbFactory


class MainActivity : AppCompatActivity() {

    private val mainAdapter  = MainAdapter(emptyList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: MyViewModel by viewModels{
            MyViewModelFactory(CountriesRepositoryImpl(api = ApiFactory.create(), appDatabase = dbFactory.create(context = this)), CountriesListFilterImpl())
        }
        model.filteredCountryData.observe(this, Observer { updatedCountryList ->
            showData(updatedCountryList)
        })
        val editText:EditText = findViewById(R.id.editText)

        editText.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                model.dataFilter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        })

        recyclerView.apply {
            layoutManager = LinearLayoutManager( this@MainActivity )
            adapter = mainAdapter
        }
   }
    private fun showData(countries: List<Country>) {
            mainAdapter.updateCountriesList(countries)
    }
}
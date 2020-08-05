package com.example.newkorona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.EditText
import com.example.newkorona.filter.CountriesListFilter
import com.example.newkorona.filter.CountriesListFilterImpl
import com.example.newkorona.repository.CountriesRepository
import com.example.newkorona.repository.CountriesRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    private val mainAdapter  = MainAdapter(emptyList())
    private val countryListFilter: CountriesListFilter = CountriesListFilterImpl()
    private var countryList: List<Country> = emptyList()

    private val api:ApiService = ApiFactory.create()
    private val countryRepository : CountriesRepository? = CountriesRepositoryImpl(api = api)

    private lateinit var dataBase: AppDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataBase = dbFactory.create(this)

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


        countryRepository
            ?.fetchAllCountriesSingle()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.onErrorReturnItem(
                CountryEntityToCountryMapping.create(dataBase.countryDAO().getAll())
            )
            ?.subscribe({
                countryList= it
                val countryEntityList = CountryToCountryEntityMapping.create(countryList)

                dataBase.countryDAO()
                    .insertAll(countryEntityList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe() // I don't know why would I remove this line, yup that one must stay for without

                    dataBase.countryDAO().delete(dataBase.countryDAO().getAll())
                    dataBase.countryDAO().insertAll(countryEntityList)
                d("precise_tag","number of countries: ${countryList.size}")
                showData(countryList)
            },{

            })

   }
    private fun showData(countries: List<Country>) {
            mainAdapter.updateCountriesList(countries)
    }

}


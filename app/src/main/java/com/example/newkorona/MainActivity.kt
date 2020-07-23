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
                dataBase.countryDAO().getAll().map { CountryEntity -> Country(
                    country = CountryEntity.country,
                    cases = CountryEntity.cases,
                    todayCases = CountryEntity.todayCases,
                    deaths = CountryEntity.deaths,
                    todayDeaths = CountryEntity.todayDeaths,
                    recovered = CountryEntity.recovered,
                    active = CountryEntity.active,
                    casesPerOneMillion = CountryEntity.casesPerOneMillion,
                    totalTests = CountryEntity.totalTests,
                    testsPerOneMillion = CountryEntity.testsPerOneMillion,
                    deathsPerOneMillion = CountryEntity.deathsPerOneMillion,
                    critical = CountryEntity.critical
                    )
                }
            )
            ?.subscribe({
                countryList= it
                val countryEntityList = countryList.map {  country -> CountryEntity(
                    country = country.country,
                    cases = country.cases,
                    todayCases = country.todayCases,
                    deaths = country.deaths,
                    todayDeaths = country.todayDeaths,
                    recovered = country.recovered,
                    active = country.active,
                    casesPerOneMillion = country.casesPerOneMillion,
                    totalTests = country.totalTests,
                    testsPerOneMillion = country.testsPerOneMillion,
                    deathsPerOneMillion = country.deathsPerOneMillion,
                    critical = country.critical
                    )
                }


                dataBase.countryDAO()
                    .insertAll(countryEntityList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()


                    dataBase.clearAllTables()
                    dataBase.countryDAO().insertAll(countryEntityList)
                showData(countryList)
            },{

            })

   }
    private fun showData(countries: List<Country>) {
            mainAdapter.updateCountriesList(countries)
    }

}


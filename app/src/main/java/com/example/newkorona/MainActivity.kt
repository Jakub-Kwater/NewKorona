package com.example.newkorona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.EditText
import androidx.room.Database
import androidx.room.RoomDatabase
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
    private var countryEntityList: List<CountryEntity> = emptyList()




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
            ?.subscribe({
                countryList= it
                for (c in it.indices){
                    countryEntityList.get(c).deaths = it.get(c).deaths // changed val into var in CountryEntity, this does not seem to be correct method
                }
                dataBase.countryDAO().insertAll(countryEntityList)
                showData(countryList)
            },{

            })

        //        dataBase.countryDAO().insertCountry(CountryEntity(country = "A", cases = 2, todayCases = 2, deaths = 2, todayDeaths = 2, recovered = 2, active = 2, critical = 2, deathsPerOneMillion = 2, testsPerOneMillion = 2, totalTests = 2, casesPerOneMillion = 2))
        val allCountries = dataBase.countryDAO().getAll()
        d("country size", "getAll size = ${allCountries.size}")

   }
    private fun showData(countries: List<Country>) {
            mainAdapter.updateCountriesList(countries)
    }
}


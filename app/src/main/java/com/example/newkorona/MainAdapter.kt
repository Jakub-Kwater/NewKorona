package com.example.newkorona

import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.data_row.view.*

class MainAdapter(private var countries: List<Country>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]

        val newCountry:String = "Country: ${country.country}"
        val newCases:String = "Cases: ${country.cases}"
        val newTodayCases:String = "Today cases: ${country.todayCases}"
        val newDeaths:String = "Deaths: ${country.deaths}"
        val newDeathsToday:String = "New deaths today: ${country.todayDeaths}"
        val newRecovered:String = "Recovered: ${country.recovered}"
        val newActive:String = "Active: ${country.active}"
        val newCritical:String = "Critical ${country.critical}"
        val newCasesPM = "Cases per million: ${country.casesPerOneMillion}"
        val newDeathsPM = "Deaths per million: ${country.deathsPerOneMillion}"
        val newTotalTests:String = "Total tests: ${country.totalTests}"
        val newTestsPM:String = "Tests per million: ${country.testsPerOneMillion}"

        //d("TAG_KORONA", "List  size: " + country.cases)

        holder.country.text = newCountry
        holder.cases.text = newCases
        holder.todayCases.text = newTodayCases
        holder.deaths.text = newDeaths
        holder.todayDeaths.text = newDeathsToday
        holder.recovered.text = newRecovered
        holder.active.text = newActive
        holder.critical.text = newCritical
        holder.casesPM.text = newCasesPM
        holder.deathsPM.text = newDeathsPM
        holder.totalTest.text = newTotalTests
        holder.testsPM.text = newTestsPM
    }

    fun updateCountriesList (newCountries: List<Country>) {
        countries = newCountries
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val country: TextView = itemView.country_view
        val cases: TextView = itemView.cases_view
        val todayCases:TextView = itemView.todayCases_view
        val deaths:TextView = itemView.deaths_view
        val todayDeaths:TextView = itemView.todayDeaths_view
        val recovered:TextView = itemView.recovered_view
        val active:TextView = itemView.active_view
        val critical:TextView = itemView.critical_view
        val casesPM:TextView = itemView.casesPM_view
        val deathsPM:TextView = itemView.deathsPM_view
        val totalTest:TextView = itemView.totalTests_view
        val testsPM:TextView =  itemView.testsPM_view


    }

    override fun getItemCount(): Int = countries.size
}

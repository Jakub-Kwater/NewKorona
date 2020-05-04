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

class MainAdapter(private val countries: List<Country>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        //d("TAG_KORONA", "List  size: " + country.cases)
        holder.country.text = country.country
        holder.cases.text = country.recovered.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val country: TextView = itemView.country_view
        val cases: TextView = itemView.cases_view
    }
}

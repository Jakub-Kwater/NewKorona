package com.example.newkorona

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.data_row.view.*

class MainAdapter(val data: Array<Data>) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.data_row,parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return data.size

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val country: Data = data.get(position)

        holder.view.country_view.text = country.country.toString()
        holder.view.cases_view.text = country.cases.toString()

    }
}

class CustomViewHolder(val view: View):RecyclerView.ViewHolder(view) {

}

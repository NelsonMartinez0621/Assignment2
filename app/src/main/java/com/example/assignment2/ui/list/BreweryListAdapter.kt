package com.example.assignment2.ui.list


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.data.model.BreweryItem
import com.example.assignment2.databinding.ItemBreweryBinding

class BreweryListAdapter(
    val data: ArrayList<BreweryItem>
) : RecyclerView.Adapter<BreweryListAdapter.ViewHolder>(){

    inner class ViewHolder(val view: ItemBreweryBinding):RecyclerView.ViewHolder(view.root){
        fun initUI(breweryItem: BreweryItem) {
            view.apply {
                this.tvName.text = breweryItem.name
                this.tvBreweryType.text = "${tvBreweryType.text} ${breweryItem.breweryType?.uppercase()}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemBreweryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initUI(data[position])
    }

    override fun getItemCount(): Int = data.size
}
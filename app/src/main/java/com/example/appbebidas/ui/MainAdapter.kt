package com.example.appbebidas.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appbebidas.R
import com.example.appbebidas.base.BaseViewHolder
import com.example.appbebidas.data.model.Drink
import kotlinx.android.synthetic.main.tragos_row.view.*

class MainAdapter(
    private val context: Context, private val tragosList: MutableList<Drink>,
    private val itemClickListener: OnTragoClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnTragoClickListener {
        fun onTragoClick(drink: Drink,position: Int)
    }

    fun deleteDrink(position: Int){
        tragosList.removeAt(position)
        notifyItemRemoved(position)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.tragos_row, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return tragosList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(tragosList[position], position)
        }
    }

    // se usar un inner class para que esta clase se quite de memoria junto con el Adaptador
    inner class MainViewHolder(itemView: View) : BaseViewHolder<Drink>(itemView) {
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(itemView.img_trago)
            itemView.txt_title.text = item.name
            itemView.txt_description.text = item.description
            itemView.setOnClickListener{itemClickListener.onTragoClick(item,position)
            }
        }

    }


}
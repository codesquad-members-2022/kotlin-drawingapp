package com.codesquad_han.kotlin_drawingapp.rectangle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.codesquad_han.kotlin_drawingapp.R
import com.codesquad_han.kotlin_drawingapp.databinding.ItemRecyclerviewObjectListBinding
import com.codesquad_han.kotlin_drawingapp.model.rectangle.BaseRectangle

class ObjectListAdapter(var dataSet: List<BaseRectangle>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(val binding: ItemRecyclerviewObjectListBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_object_list, parent, false)
        return ViewHolder(ItemRecyclerviewObjectListBinding.bind(view))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            holder.binding.tvObjectName.text = dataSet[position].getObjectName()

            if(dataSet[position].selected) holder.binding.root.setBackgroundResource(R.color.item_selected)
            else holder.binding.root.setBackgroundResource(R.color.item_unselected)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateDataSet(newList: List<BaseRectangle>){
        dataSet = newList
        notifyDataSetChanged()
    }
}
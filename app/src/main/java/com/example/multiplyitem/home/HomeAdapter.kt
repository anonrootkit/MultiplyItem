package com.example.multiplyitem.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.multiplyitem.databinding.ListItemBinding

class HomeAdapter(context: Context, private val listener: OnItemClickListener)
    : ArrayAdapter<Data>(context, 0, ArrayList()) {

    private val dataList = ArrayList<Data>()

    fun initList(list : List<Data>){
        dataList.clear()
        dataList.addAll(list)
        this.notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding : ListItemBinding =
            if (convertView == null)
                ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            else
                ListItemBinding.bind(convertView)

        val data = dataList[position]

        if (data.isSelected){
            binding.root.setBackgroundColor(Color.LTGRAY)
        }else{
            binding.root.setBackgroundColor(0)
        }

        binding.root.setOnClickListener {
            if (data.isSelectable)
                listener.onItemClick(position)
        }

        binding.number.text = "${data.value}"

        return binding.root
    }

}

interface OnItemClickListener{
    fun onItemClick(position: Int)
}
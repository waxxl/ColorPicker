package com.story.creator.picker.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.story.creator.picker.R
import com.story.creator.picker.model.bean.ColorItem
import kotlinx.android.synthetic.main.palette_detail_item.view.*

class PaletteDetailAdapter : RecyclerView.Adapter<PaletteDetailAdapter.ViewHolder>() {
    var colorData = ArrayList<ColorItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaletteDetailAdapter.ViewHolder {
        var view = View.inflate(parent.context, R.layout.palette_detail_item, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return colorData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.backgroud.setBackgroundColor(colorData.get(position).getColor())
        holder.text.setText(colorData.get(position).getColor().toString())
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var backgroud = itemView.findViewById<FrameLayout>(R.id.palette_detail)
        var text = itemView.findViewById<TextView>(R.id.color_text)
    }
}
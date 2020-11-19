package com.story.creator.picker.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.story.creator.picker.R
import com.story.creator.picker.model.bean.PaletteModules
import com.story.creator.picker.util.Util
import com.story.creator.picker.view.PaletteView

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    var paletteModules = ArrayList<PaletteModules>()

    constructor() {
        paletteModules = Util.queryAllFavorite()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        var view = View.inflate(parent.context, R.layout.favorite_item, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return paletteModules.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var array = paletteModules.get(position).colorArray

        var array2: IntArray = IntArray(array.size)
        for ((index, colorValue) in array.withIndex()) {
            array2[index] = colorValue.value
        }
        holder.palette_view.setColor(array2)
        holder.favorite_palette.setBackgroundColor(paletteModules.get(position).colorArray.get(0).value)
        holder.palette_name.text = paletteModules.get(position).name
        holder.collect_img.isSelected = paletteModules.get(position).isFavorite
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var palette_view = itemView.findViewById<PaletteView>(R.id.palette_view)
        var favorite_palette = itemView.findViewById<RelativeLayout>(R.id.bottom_palette)
        var palette_name = itemView.findViewById<TextView>(R.id.palette_name)
        var collect_img = itemView.findViewById<ImageView>(R.id.collect_img)
    }
}
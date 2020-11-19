package com.story.creator.picker.activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.story.creator.picker.R
import com.story.creator.picker.adapter.FavoriteAdapter
import kotlinx.android.synthetic.main.activity_favourite.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class FavouritesActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_favourite)
        title_toolbar.text = getString(R.string.favourites)

        back_toolbar.setOnClickListener(this)

        recycler_favorite.setHasFixedSize(false)
        recycler_favorite.layoutManager = GridLayoutManager(this, 2)
        recycler_favorite.adapter = FavoriteAdapter()
        recycler_favorite.addOnItemTouchListener(object : AdapterView.OnItemClickListener,
            RecyclerView.OnItemTouchListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

            }

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.back_toolbar -> {
                onBackPressed()
            }
        }
    }
}
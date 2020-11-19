package com.story.creator.picker.view

import android.util.Log

class Listener {
    internal interface Text {
        fun add()
        fun delete()
    }

    fun `fun`() {
        val text: Text =
            object : Text {
                override fun add() {
                    Log.d("xxl", "add")
                }

                override fun delete() {
                    Log.d("xxl", "delete")
                }
            }
    }
}
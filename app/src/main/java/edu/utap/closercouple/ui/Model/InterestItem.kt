package edu.utap.closercouple.ui.Model

import android.graphics.Color
import edu.utap.closercouple.R



data class InterestItem(val i: Int, var name: String, val ic: Int, val sel: Boolean) {
    var color: Int = 0
    var selected: Boolean = false
    var icon = R.drawable.ic_call_24dp
    init {
        this.color = Color.rgb(i shr 16 and 0xFF, i shr 8 and 0xFF, i and 0xFF)
        this.icon = ic
        this.selected = sel
    }
}

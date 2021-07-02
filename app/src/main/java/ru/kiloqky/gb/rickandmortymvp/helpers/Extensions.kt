package ru.kiloqky.gb.rickandmortymvp.helpers

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun Fragment.arguments(vararg arguments: Pair<String, Any>): Fragment {
    this.arguments = bundleOf(*arguments)
    return this
}

fun View.click(click: () -> Unit) = setOnClickListener { click() }

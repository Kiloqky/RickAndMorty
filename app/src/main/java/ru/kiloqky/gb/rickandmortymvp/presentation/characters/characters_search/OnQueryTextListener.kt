package ru.kiloqky.gb.rickandmortymvp.presentation.characters.characters_search

import android.widget.SearchView

open class OnQueryTextListener: SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
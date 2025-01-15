package com.ase.pokedex.ex

import android.content.Context
import android.util.Log
import android.widget.Toast

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.log() {
    Log.d("Pokédex", this)
}

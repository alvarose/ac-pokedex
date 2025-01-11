package com.ase.pokedex.common.ex

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import java.security.MessageDigest
import kotlin.text.toByteArray
import kotlin.text.toHexString

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.log() {
    Log.d("Pokédex", this)
}

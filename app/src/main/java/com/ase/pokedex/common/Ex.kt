package com.ase.pokedex.common

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import java.security.MessageDigest
import kotlin.text.toByteArray
import kotlin.text.toHexString

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalStdlibApi::class)
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(this.toByteArray())
    return digest.toHexString()
}

fun Bitmap.trimTransparent(): Bitmap {
    val width = width
    val height = height
    var top = 0
    var left = 0
    var right = width
    var bottom = height

    // Top
    for (y in 0 until height) {
        var found = false
        for (x in 0 until width) {
            if (getPixel(x, y) != 0) {
                top = y
                found = true
                break
            }
        }
        if (found) break
    }

    // Bottom
    for (y in height - 1 downTo 0) {
        var found = false
        for (x in 0 until width) {
            if (getPixel(x, y) != 0) {
                bottom = y
                found = true
                break
            }
        }
        if (found) break
    }

    // Start
    for (x in 0 until width) {
        var found = false
        for (y in 0 until height) {
            if (getPixel(x, y) != 0) {
                left = x
                found = true
                break
            }
        }
        if (found) break
    }

    // End
    for (x in width - 1 downTo 0) {
        var found = false
        for (y in 0 until height) {
            if (getPixel(x, y) != 0) {
                right = x
                found = true
                break
            }
        }
        if (found) break
    }

    return Bitmap.createBitmap(this, left, top, right - left, bottom - top)
}

package com.ase.pokedex.domain

import kotlin.collections.isNotEmpty

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokeType> = emptyList<PokeType>(),
    val favorite: Boolean,
) {
    val avatar: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

    val image: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

    fun hasDataComplete() = types.isNotEmpty()
}

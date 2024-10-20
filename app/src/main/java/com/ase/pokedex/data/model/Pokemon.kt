package com.ase.pokedex.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokeType> = emptyList<PokeType>(),
) {
    val avatar: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

    val image: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}

fun String.formatPokemonName(): String {
    return this.replaceFirstChar(Char::titlecase).replace("-f", " ♀").replace("-m", " ♂")
}

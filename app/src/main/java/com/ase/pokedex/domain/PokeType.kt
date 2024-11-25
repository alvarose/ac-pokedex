package com.ase.pokedex.domain

enum class PokeType(
    val id: Int,
    val value: String,
    val url: String,
    val color: Long,
) {
    NORMAL(1, "Normal", "https://pokeapi.co/api/v2/type/1/", 0xFFA8A878),
    FIGHTING(2, "Lucha", "https://pokeapi.co/api/v2/type/2/", 0xFFC03028),
    FLYING(3, "Volador", "https://pokeapi.co/api/v2/type/3/", 0xFFA890F0),
    POISON(4, "Veneno", "https://pokeapi.co/api/v2/type/4/", 0xFFA040A0),
    GROUND(5, "Tierra", "https://pokeapi.co/api/v2/type/5/", 0xFFE0C068),
    ROCK(6, "Roca", "https://pokeapi.co/api/v2/type/6/", 0xFFB8A038),
    BUG(7, "Bicho", "https://pokeapi.co/api/v2/type/7/", 0xFFA8B820),
    GHOST(8, "Fantasma", "https://pokeapi.co/api/v2/type/8/", 0xFF705898),
    STEEL(9, "Acero", "https://pokeapi.co/api/v2/type/9/", 0xFFB8B8D0),
    FIRE(10, "Fuego", "https://pokeapi.co/api/v2/type/10/", 0xFFF08030),
    WATER(11, "Agua", "https://pokeapi.co/api/v2/type/11/", 0xFF6890F0),
    GRASS(12, "Planta", "https://pokeapi.co/api/v2/type/12/", 0xFF78C850),
    ELECTRIC(13, "Eléctrico", "https://pokeapi.co/api/v2/type/13/", 0xFFF8D030),
    PSYCHIC(14, "Psíquico", "https://pokeapi.co/api/v2/type/14/", 0xFFF85888),
    ICE(15, "Hielo", "https://pokeapi.co/api/v2/type/15/", 0xFF98D8D8),
    DRAGON(16, "Dragón", "https://pokeapi.co/api/v2/type/16/", 0xFF7038F8),
    DARK(17, "Siniestro", "https://pokeapi.co/api/v2/type/17/", 0xFF705848),
    FAIRY(18, "Hada", "https://pokeapi.co/api/v2/type/18/", 0xFFEE99AC),
    UNKNOWN(19, "???", "https://pokeapi.co/api/v2/type/10001/", 0xFF68A090);

    companion object {
        fun fromName(name: String): PokeType {
            return requireNotNull(
                entries.find { it.name == name.uppercase() }
            )
        }
    }
}

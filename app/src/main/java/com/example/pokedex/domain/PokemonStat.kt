package com.example.pokedex.domain

data class PokemonStat(
    val base_stat: Int,
    val stat: Stat
)

data class Stat(
    val name: String
)

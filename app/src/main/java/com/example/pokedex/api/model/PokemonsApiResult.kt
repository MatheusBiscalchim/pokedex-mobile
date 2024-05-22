package com.example.pokedex.api.model

import com.example.pokedex.domain.PokemonStat
import com.example.pokedex.domain.PokemonType

data class PokemonsApiResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)

data class PokemonApiResult(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeSlot>,
    val height: Float,
    val weight: Float,
    val stats: List<PokemonStat>
)

data class PokemonTypeSlot (
    val slot: Int,
    val type: PokemonType
)

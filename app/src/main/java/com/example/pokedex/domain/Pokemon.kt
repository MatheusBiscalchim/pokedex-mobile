package com.example.pokedex.domain

data class Pokemon(
    val number : Int,
    val name : String,
    val types: List<PokemonType>,
    val height: Float,
    val weight: Float,
    val stats: List<PokemonStat>

) {
    val formattedName = name.capitalize()

    val formattedNumber = number.toString().padStart(3,'0')

    val formattedHeight = (height/10).toString()

    val formattedWeight = (weight/10).toString()

    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$formattedNumber.png"
}
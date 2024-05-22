package com.example.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.api.PokemonRepository
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.domain.PokemonStat

class PokemonViewModel : ViewModel() {
    var pokemons = MutableLiveData<List<Pokemon?>>()

    init {
        Thread(Runnable {
           loadPokemons()
//            loadPerFire()
//            laodPerGrass()
        }).start()
    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let { results ->
            val pokemonList = mutableListOf<Pokemon?>()
            results.forEach { pokemonResult ->
                val number = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()

                val pokemonApiResult = PokemonRepository.getPokemon(number)

                pokemonApiResult?.let {
                    val pokemon = Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { typeSlot ->
                            typeSlot.type
                        },
                        pokemonApiResult.height,
                        pokemonApiResult.weight,
                        pokemonApiResult.stats
                    )
                    pokemonList.add(pokemon)
                }
            }
            pokemons.postValue(pokemonList)
        }
    }


    private fun loadPerFire() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let { results ->
            val pokemonList = mutableListOf<Pokemon?>()
            results.forEach { pokemonResult ->
                val number = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()

                val pokemonApiResult = PokemonRepository.getPokemon(number)

                pokemonApiResult?.let {
                    val pokemon = Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { typeSlot ->
                            typeSlot.type
                        },
                        pokemonApiResult.height,
                        pokemonApiResult.weight,
                        pokemonApiResult.stats
                    )
                    if (pokemon.types.any { it.name == "fire" }) {
                        pokemonList.add(pokemon)
                    }
                }
            }
            pokemons.postValue(pokemonList)
        }
    }

    private fun loadPerGrass() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let { results ->
            val pokemonList = mutableListOf<Pokemon?>()
            results.forEach { pokemonResult ->
                val number = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()

                val pokemonApiResult = PokemonRepository.getPokemon(number)

                pokemonApiResult?.let {
                    val pokemon = Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { typeSlot ->
                            typeSlot.type
                        },
                        pokemonApiResult.height,
                        pokemonApiResult.weight,
                        pokemonApiResult.stats
                    )
                    if (pokemon.types.any { it.name == "grass" }) {
                        pokemonList.add(pokemon)
                    }
                }
            }
            pokemons.postValue(pokemonList)
        }
    }
}

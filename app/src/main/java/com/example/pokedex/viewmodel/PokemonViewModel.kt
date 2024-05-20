package com.example.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.api.PokemonRepository
import com.example.pokedex.domain.Pokemon

class PokemonViewModel : ViewModel() {
    var pokemons = MutableLiveData<List<Pokemon?>>()

    init {
        Thread(Runnable {
            loadPokemons()
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
                        }
                    )
                    pokemonList.add(pokemon)
                }
            }
            pokemons.postValue(pokemonList)
        }
    }

    private fun loadPokemonsTypes() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let { results ->
            val pokemonList = mutableListOf<Pokemon?>()
            results.forEach { pokemonResult ->
                val tipo = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/type/", "")
                    .replace("/", "")

                val pokemonApiResult = PokemonRepository.getTypesPokemon(tipo)

                pokemonApiResult?.let {
                    val pokemon = Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { typeSlot ->
                            typeSlot.type
                        }
                    )
                    pokemonList.add(pokemon)
                }
            }
            pokemons.postValue(pokemonList)
        }
    }
}

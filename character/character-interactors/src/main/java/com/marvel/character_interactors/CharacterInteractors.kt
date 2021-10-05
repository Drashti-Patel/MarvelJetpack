package com.marvel.character_interactors

import com.marvel.character_datasource.network.CharacterService

data class CharacterInteractors(
    val getCharacters: GetCharacters,
) {
    companion object Factory {
        fun build(): CharacterInteractors{
            val service = CharacterService.build()
            return CharacterInteractors(
                getCharacters = GetCharacters(
                    service = service,
                ),
            )
        }
    }
}
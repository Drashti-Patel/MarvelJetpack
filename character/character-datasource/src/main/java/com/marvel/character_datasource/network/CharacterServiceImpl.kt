package com.marvel.character_datasource.network

import com.marvel.character_domain.Character
import io.ktor.client.*
import io.ktor.client.request.*

class CharacterServiceImpl(
    private val httpClient: HttpClient,
): CharacterService {
    override suspend fun getCharacters(): Character {
        return httpClient.get<CharacterDto> {
            url(APIEndPoints.CHARACTER_URL)
        }.toCharacter()
    }
}
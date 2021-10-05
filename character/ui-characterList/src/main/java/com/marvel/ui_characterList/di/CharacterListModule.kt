package com.marvel.ui_characterList.di

import com.marvel.character_interactors.CharacterInteractors
import com.marvel.character_interactors.GetCharacters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterListModule {

    @Provides
    @Singleton
    fun getCharacters(
        interactors: CharacterInteractors
    ): GetCharacters{
        return interactors.getCharacters
    }
}
package com.marvel.di

import com.marvel.character_interactors.CharacterInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterInteractorsModule {

    @Provides
    @Singleton
    fun provideCharacterInteractors(
    ): CharacterInteractors{
        return CharacterInteractors.build()
    }
}
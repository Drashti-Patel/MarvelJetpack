package com.marvel.character_interactors

import com.marvel.character_datasource.network.CharacterService
import com.marvel.character_domain.Character.Data.CharacterInfo
import com.marvel.core.DataState
import com.marvel.core.ProgressBarState
import com.marvel.core.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharacters(
    private val service: CharacterService,
    ) {
    fun execute(): Flow<DataState<List<CharacterInfo>>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            val characters: List<CharacterInfo> = try {
                service.getCharacters().data.results as ArrayList<CharacterInfo>
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    DataState.Response<List<CharacterInfo>>(
                        uiComponent = UIComponent.Dialog(
                            title = "Network Data Error",
                            description = e.message ?: "Unknown error"
                        )
                    )
                )
                listOf()
            }
            emit(DataState.Data(characters))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response<List<CharacterInfo>>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown error"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}
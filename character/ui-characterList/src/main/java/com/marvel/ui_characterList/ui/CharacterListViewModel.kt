package com.marvel.ui_characterList.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.character_interactors.GetCharacters
import com.marvel.core.DataState
import com.marvel.core.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel
@Inject
constructor(
    private val getCharacters: GetCharacters
    ): ViewModel(){

    val characterState: MutableState<CharacterListState> = mutableStateOf(CharacterListState())

    init {
        getCharacters()
    }

    private fun getCharacters(){
        getCharacters.execute().onEach { dataState ->
            when(dataState){
                is DataState.Response -> {
                    when(dataState.uiComponent){
                        is UIComponent.Dialog -> {
                        }
                        is UIComponent.None -> {
                        }
                    }
                }
                is DataState.Data -> {
                    characterState.value = characterState.value.copy(characters = dataState.data?: listOf())
                }
                is DataState.Loading -> {
                    characterState.value = characterState.value.copy(progressbarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

}
package com.marvel.ui_characterList.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.marvel.core.ProgressBarState
import com.marvel.ui_characterList.components.CharacterListItem

@Composable
fun CharacterList(
    state: CharacterListState,
    imageLoader: ImageLoader,
){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn{
            items(state.characters){ character ->
                CharacterListItem(
                    character = character,
                    onSelectHero = {},
                    imageLoader = imageLoader,
                )
            }
        }
        if(state.progressbarState is ProgressBarState.Loading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
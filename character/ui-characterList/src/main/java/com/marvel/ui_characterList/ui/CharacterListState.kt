package com.marvel.ui_characterList.ui

import com.marvel.character_domain.Character.Data.CharacterInfo
import com.marvel.core.ProgressBarState

data class CharacterListState(
    val progressbarState: ProgressBarState = ProgressBarState.Idle,
    val characters: List<CharacterInfo> = listOf(),
)
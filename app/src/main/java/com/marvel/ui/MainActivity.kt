package com.marvel.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.marvel.ui.theme.MarvelTheme
import com.marvel.ui_characterList.ui.CharacterList
import com.marvel.ui_characterList.ui.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarvelTheme {
                val viewModel: CharacterListViewModel = hiltViewModel()
                CharacterList(
                    state = viewModel.characterState.value,
                    imageLoader = imageLoader
                )
            }
        }
    }
}
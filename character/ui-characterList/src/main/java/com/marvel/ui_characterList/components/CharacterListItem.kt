package com.marvel.ui_characterList.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.marvel.character_domain.Character
import com.marvel.ui_characterList.R

@Composable
fun CharacterListItem(
    character: Character.Data.CharacterInfo,
    onSelectHero: (Int) -> Unit,
    imageLoader: ImageLoader,
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .clickable {
                onSelectHero(character.id)
            }
        ,
        elevation = 8.dp
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
            ){
            val painter = rememberImagePainter(
                character.thumbnail.getUrl(),
                imageLoader = imageLoader,
                builder = {
                    placeholder(if(isSystemInDarkTheme()) R.drawable.black_background else R.drawable.white_background)
                }
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray)
                ,
                painter = painter,
                contentDescription = character.name,
                contentScale = ContentScale.Crop
            )
            Column(
               modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 4.dp),
                    text = character.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h4,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }

        }
    }
}
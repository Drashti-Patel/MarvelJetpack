package com.marvel.character_datasource.network

import com.marvel.character_datasource.network.CharacterDto.DataDto
import com.marvel.character_domain.Character
import com.marvel.character_domain.Character.Data.CharacterInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("attributionHTML") val attributionHTML: String,
    @SerialName("attributionText") val attributionText: String,
    @SerialName("code") val code: Int,
    @SerialName("copyright") val copyright: String,
    @SerialName("data") val `data`: DataDto,
    @SerialName("etag") val etag: String,
    @SerialName("status") val status: String
) {
    @Serializable
    data class DataDto(
        @SerialName("count") val count: Int,
        @SerialName("limit") val limit: Int,
        @SerialName("offset") val offset: Int,
        @SerialName("results") val results: List<CharacterInfoDto>,
        @SerialName("total") val total: Int
    ) {
        @Serializable
        data class CharacterInfoDto(
            @SerialName("id") val id: Int,
            @SerialName("name") val name: String,
            @SerialName("description") val description: String?,
            @SerialName("thumbnail") val thumbnail: ThumbnailDto
        ) {
            @Serializable
            data class ThumbnailDto(
                @SerialName("path") val path: String,
                @SerialName("extension") val extension: String
            )
        }
    }
}

fun CharacterDto.toCharacter(): Character {
    return Character(
        attributionHTML = attributionHTML,
        attributionText = attributionText,
        code = code,
        copyright = copyright,
        `data` = data.getData(),
        etag = etag,
        status = status
    )
}

fun DataDto.getData(): Character.Data {
    return Character.Data(
        count = count,
        limit = limit,
        offset = offset,
        results = results.map { getResults(it) },
        total = total
    )
}

fun DataDto.CharacterInfoDto.ThumbnailDto.getThumbNail(): CharacterInfo.Thumbnail {
    return CharacterInfo.Thumbnail(
        path = path,
        extension = extension
    )
}

fun getResults(results: DataDto.CharacterInfoDto): CharacterInfo {
    return CharacterInfo(
        id = results.id,
        name = results.name,
        description = results.description,
        thumbnail = results.thumbnail.getThumbNail()
    )
}


package com.marvel.character_domain

data class Character(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
) {
    data class Data(
        val count: Int,
        val limit: Int,
        val offset: Int,
        val results: List<CharacterInfo>,
        val total: Int
    ) {
        data class CharacterInfo(
            val id: Int,
            val name: String,
            val description: String?,
            val thumbnail: Thumbnail
        ) {
            data class Thumbnail(
                val path: String,
                val extension: String
            ) {
                fun getUrl() = "$path.$extension".replace("http://", "https://")
            }
        }
    }
}
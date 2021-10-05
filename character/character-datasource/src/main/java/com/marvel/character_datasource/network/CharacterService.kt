package com.marvel.character_datasource.network

import com.marvel.character_domain.Character
import com.marvel.constants.md5
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import okhttp3.Interceptor
import kotlin.math.abs
import kotlin.random.Random

interface CharacterService {

    suspend fun getCharacters(): Character

    companion object Factory {
        fun build(): CharacterService {
            return CharacterServiceImpl(
                httpClient = HttpClient(OkHttp) {
                    engine {
                        config {
                            retryOnConnectionFailure(true)
                        }
                        addInterceptor(getInterceptor())

                        /* get solution
                            if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.level = HttpLoggingInterceptor.Level.BODY
                        addInterceptor(logging)
                         }*/
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(
                            kotlinx.serialization.json.Json  {
                                ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                            }
                        )
                    }
                    install(HttpTimeout) {
                        socketTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
                    }
                    expectSuccess = false
                }
            )
        }

        private fun getInterceptor(): Interceptor {
            val tsId = getTsId()
            return Interceptor { chain ->
                var request = chain.request()
                val url = request.url.newBuilder()
                    .addQueryParameter(PARAM_TS, tsId)
                    .addQueryParameter(PARAM_HASH, getHash(tsId))
                    .addQueryParameter(PARAM_API_KEY, InternalConfig.PUBLIC_KEY)
                    .build()
                request = request.newBuilder()
                    .url(url).build()
                chain.proceed(request)
            }
        }

        private fun getTsId() = abs(Random.nextLong()).toString()
        private fun getHash(tsId: String): String {
            return (tsId + InternalConfig.PRIVATE_KEY + InternalConfig.PUBLIC_KEY).md5()
        }

        private const val PARAM_TS = "ts"
        private const val PARAM_HASH = "hash"
        private const val PARAM_API_KEY = "apikey"
    }

}

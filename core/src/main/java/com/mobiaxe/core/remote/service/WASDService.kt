package com.mobiaxe.core.remote.service

import com.mobiaxe.core.data.Game
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface WASDService {

    @POST("games")
    suspend fun getGames(@Body request: RequestBody): List<Game>


}
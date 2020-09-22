package vaida.dryzaite.begoodapp.repository

import androidx.lifecycle.LiveData
import vaida.dryzaite.begoodapp.data.remote.responses.ImageResponse
import vaida.dryzaite.begoodapp.model.Challenge
import vaida.dryzaite.begoodapp.utils.Resource

interface ChallengeRepo {

    fun observeItems(): LiveData<List<Challenge>>

    suspend fun getItems(): List<Challenge>

    fun observeItemById(id: Int): LiveData<Challenge>

    suspend fun getItemsById(id: Int): Challenge

    suspend fun insertItem(challenge: Challenge)

    suspend fun insertItemsList(challenges: List<Challenge>)

    suspend fun updateCompleted(id: Int, completed: Boolean)

    suspend fun deleteItem(challenge: Challenge)

    suspend fun deleteAll()

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>

}
package vaida.dryzaite.begoodapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import vaida.dryzaite.begoodapp.data.remote.responses.ImageResponse
import vaida.dryzaite.begoodapp.model.Challenge
import vaida.dryzaite.begoodapp.utils.Resource

// Fake test double for repo is best way to test VM, coz no need to make network requests, or access DB.
// all the functions are fake works as they suppose to, except on fake data

class FakeChallengesRepository: ChallengeRepo {

    private val challengesList = mutableListOf<Challenge>()

    private val observableChallenges = MutableLiveData<List<Challenge>>(challengesList)
    private val observableChallenge = MutableLiveData<Challenge>()


    //to simulate network errors
    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableChallenges.postValue(challengesList)
    }

    override fun observeItems(): LiveData<List<Challenge>> {
        return observableChallenges
    }

    override suspend fun getItems(): List<Challenge> {
        return challengesList
    }

    override fun observeItemById(id: Int): LiveData<Challenge> {
        return observableChallenge
    }

    override suspend fun getItemsById(id: Int): Challenge {
        return challengesList.find { it.id == id }!!
    }

    override suspend fun insertItem(challenge: Challenge) {
        challengesList.add(challenge)
        refreshLiveData()
    }

    override suspend fun insertItemsList(challenges: List<Challenge>) {
        for (challenge in challenges) {
            challengesList.add(challenge)
        }
        refreshLiveData()
    }

    override suspend fun updateCompleted(id: Int, completed: Boolean) {
        challengesList.find { it.id == id }!!.isCompleted = completed
        refreshLiveData()
    }

    override suspend fun deleteItem(challenge: Challenge) {
        challengesList.remove(challenge)
        refreshLiveData()
    }

    override suspend fun deleteAll() {
        challengesList.clear()
        refreshLiveData()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return  if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success((ImageResponse(listOf(), 0, 0)))
        }
    }
}
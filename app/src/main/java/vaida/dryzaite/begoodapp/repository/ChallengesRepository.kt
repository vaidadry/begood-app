package vaida.dryzaite.begoodapp.repository

import androidx.lifecycle.LiveData
import vaida.dryzaite.begoodapp.data.local.ChallengesDao
import vaida.dryzaite.begoodapp.data.remote.responses.ImageResponse
import vaida.dryzaite.begoodapp.data.remote.responses.PixabayApi
import vaida.dryzaite.begoodapp.model.Challenge
import vaida.dryzaite.begoodapp.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class ChallengesRepository @Inject constructor(
    private val challengesDao: ChallengesDao,
    private val pixabayApi: PixabayApi
): ChallengeRepo {

    override fun observeItems(): LiveData<List<Challenge>> = challengesDao.observeItems()

    override suspend fun getItems(): List<Challenge> = challengesDao.getItems()

    override fun observeItemById(id: Int): LiveData<Challenge> = challengesDao.observeItemById(id)

    override suspend fun getItemsById(id: Int): Challenge = challengesDao.getItemById(id)

    override suspend fun insertItem(challenge: Challenge) =
        challengesDao.insertItem(challenge)

    override suspend fun insertItemsList(challenges: List<Challenge>) {
        challengesDao.insertItemsList(challenges)
    }

    override suspend fun updateCompleted(id: Int, completed: Boolean) {
        challengesDao.updateCompleted(id, completed)
    }

    override suspend fun deleteItem(challenge: Challenge) {
        challengesDao.deleteItem(challenge)
    }

    override suspend fun deleteAll() {
        challengesDao.deleteAll()
    }

    //using generic Resource class to wrap response into loading statuses, and do error handling
    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayApi.searchForImages(imageQuery)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurred", null)
            } else {
                Resource.error("An unknown error occurred", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach server. Check your internet connection", null)
        }
    }

}
package vaida.dryzaite.begoodapp.ui.challenges

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vaida.dryzaite.begoodapp.data.remote.responses.ImageResponse
import vaida.dryzaite.begoodapp.model.Challenge
import vaida.dryzaite.begoodapp.repository.ChallengeRepo
import vaida.dryzaite.begoodapp.utils.Constants.MAX_DESC_LENGTH
import vaida.dryzaite.begoodapp.utils.Constants.MAX_TITLE_LENGTH
import vaida.dryzaite.begoodapp.utils.Constants.MIN_TITLE_LENGTH
import vaida.dryzaite.begoodapp.utils.Event
import vaida.dryzaite.begoodapp.utils.Resource

// adding new challenges, validate input and present them in RV
class ChallengesViewModel @ViewModelInject constructor(
    private val repository: ChallengeRepo
) : ViewModel() {

    val challengeList = repository.observeItems()

    //to hold all images found by search via API call
    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    //to hold selected image URL
    private val _curImageUrl = MutableLiveData<String>()
    val currentImageUrl: LiveData<String> = _curImageUrl

    //to hold status value of validated challenge input
    private val _insertChallengeStatus = MutableLiveData<Event<Resource<Challenge>>>()
    val insertChallengeStatus: LiveData<Event<Resource<Challenge>>> = _insertChallengeStatus


    fun setCurImageUrl(url: String) {
        _curImageUrl.postValue(url)
    }

    fun deleteChallenge(challenge: Challenge) = viewModelScope.launch {
        repository.deleteItem(challenge)
    }

    fun insertChallengeIntoDb(challenge: Challenge) = viewModelScope.launch {
        repository.insertItem(challenge)
    }

    // input validation before adding to DB
    fun insertChallenge(title: String, description: String) {
        if (title.isEmpty()) {
            _insertChallengeStatus.postValue(
                Event(
                    Resource.error(
                        "Title field must not be empty",
                        null
                    )
                )
            )
            return
        }
        if (title.length < MIN_TITLE_LENGTH) {
            _insertChallengeStatus.postValue(
                Event(
                    Resource.error(
                        "Title should be longer than $MIN_TITLE_LENGTH symbols",
                        null
                    )
                )
            )
            return
        }
        if (title.length > MAX_TITLE_LENGTH) {
            _insertChallengeStatus.postValue(
                Event(
                    Resource.error(
                        "Title should be no longer than $MAX_TITLE_LENGTH symbols",
                        null
                    )
                )
            )
            return
        }
        if (description.length > MAX_DESC_LENGTH) {
            _insertChallengeStatus.postValue(
                Event(
                    Resource.error(
                        "Description should be no longer than $MAX_DESC_LENGTH symbols",
                        null
                    )
                )
            )
            return
        }
        if (!challengeList.value?.filter {
                it.title == title
            }.isNullOrEmpty()) {
            _insertChallengeStatus.postValue(
                Event(
                    Resource.error(
                        "Challenge with identical title already exists",
                        null
                    )
                )
            )
            return
        }


        //  in case no description, use title instead
        val descriptionFinal =
            if (description.isEmpty()) {
                title
            } else
                description

        val challenge = Challenge(title, descriptionFinal, _curImageUrl.value ?: "")

        insertChallengeIntoDb(challenge)

        // resetting url value, for next input
        setCurImageUrl("")

        _insertChallengeStatus.postValue(Event(Resource.success(challenge)))
    }

    fun searchForImage(imageQuery: String) {
        if (imageQuery.isEmpty()) {
            return
        }
        // ".value" notifies all the observers, while .postValue notifies with only last value,
        // in case many are posted - important for tests
        _images.value = Event(Resource.loading(null))

        viewModelScope.launch {
            val response = repository.searchForImage(imageQuery)
            _images.value = Event(response)
        }
    }


}
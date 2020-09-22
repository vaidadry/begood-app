package vaida.dryzaite.begoodapp.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import vaida.dryzaite.begoodapp.repository.ChallengeRepo

class HomeViewModel @ViewModelInject constructor(
    private val repository: ChallengeRepo
) : ViewModel() {

//    private val repository: ChallengesRepository

//    init {
//        val challengesDao = ChallengesDatabase
//            .getInstance(application, viewModelScope, application.resources)
//            .challengesDao()
//        repository = ChallengesRepository(challengesDao)
//    }


//    private val _challengeTitle = MutableLiveData<String>().apply {
//        value = application.resources.getString(R.string.start_challenge_title)
//    }
//    val challengeTitle: LiveData<String> = _challengeTitle
//
//    private val challengeList = repository.observeItems()
//
//    private val challenges = ArrayList<Challenge>()
//
//
//    private val _counter = MutableLiveData<Int>()
//    val counter: LiveData<Int> = _counter
//
//    private val _onStartClick = MutableLiveData<Boolean>()
//    val onStartClick: LiveData<Boolean> = _onStartClick
//
//    fun onStart() {
//        _challengeTitle.value = pickChallengeTitle()
//
//        _onStartClick.value = true
//        _counter.value = 0
//    }
//
//    fun onChallengeAccepted() {
//        _challengeTitle.value = "dar kitas taskas"
//        _counter.value = _counter.value?.plus(1)
//    }
//
//    fun onChallengeDeclined() {
//        _challengeTitle.value = "dar vienas taskas"
//    }
//
//    private fun pickChallengeTitle(): String {
//       val challenge =  pickActiveChallenge(challengeList)
//        if (challenge != null) {
//            return challenge.title
//        }
//        return "Add some challenges!"
//    }
//
//
//
//    private fun pickActiveChallenge(challengeList: LiveData<List<Challenge>>): Challenge? {
//
//        if (challengeList.value.isNullOrEmpty()) {
//            return null
//        }
//        else{
//            for (challenge in challengeList.value!!)
//                if (challenge.isActive) {
//                    challenges.add(challenge)
//                }
//        }
//        return challenges.random()
//    }
}
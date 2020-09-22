package vaida.dryzaite.begoodapp.ui.scoreboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vaida.dryzaite.begoodapp.repository.ChallengeRepo

class ScoreboardViewModel @ViewModelInject constructor(
    private val repository: ChallengeRepo
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}
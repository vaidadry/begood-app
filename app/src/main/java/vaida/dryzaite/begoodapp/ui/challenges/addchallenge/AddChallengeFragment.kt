package vaida.dryzaite.begoodapp.ui.challenges.addchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import vaida.dryzaite.begoodapp.R
import vaida.dryzaite.begoodapp.databinding.FragmentAddChallengeBinding
import vaida.dryzaite.begoodapp.ui.challenges.ChallengesViewModel

@AndroidEntryPoint
class AddChallengeFragment : Fragment() {

    private lateinit var binding: FragmentAddChallengeBinding
    private val viewModel: ChallengesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_challenge ,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

}
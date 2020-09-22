package vaida.dryzaite.begoodapp.ui.challenges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import vaida.dryzaite.begoodapp.R
import vaida.dryzaite.begoodapp.databinding.FragmentChallengesBinding

@AndroidEntryPoint
class ChallengesFragment : Fragment() {

    private lateinit var binding: FragmentChallengesBinding
    private val viewModel: ChallengesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_challenges, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        return binding.root
    }
}
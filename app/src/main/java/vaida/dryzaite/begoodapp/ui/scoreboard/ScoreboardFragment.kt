package vaida.dryzaite.begoodapp.ui.scoreboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import vaida.dryzaite.begoodapp.R
import vaida.dryzaite.begoodapp.databinding.FragmentScoreboardBinding

@AndroidEntryPoint
class ScoreboardFragment : Fragment() {

    private lateinit var binding: FragmentScoreboardBinding
    private val viewModel: ScoreboardViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scoreboard, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.text.observe(viewLifecycleOwner, {
            binding.textScoreboard.text = it
        })
        return binding.root
    }
}
package vaida.dryzaite.begoodapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import vaida.dryzaite.begoodapp.R
import vaida.dryzaite.begoodapp.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

//        viewModel.challengeTitle.observe(viewLifecycleOwner, {
//            binding.challengeTitle.text = it
//
//        })
//
//        viewModel.counter.observe(viewLifecycleOwner, {
//            binding.counter.text = it.toString()
//        })
//
//        viewModel.onStartClick.observe(viewLifecycleOwner, {
//           if (it) {
//               binding.start.visibility = View.INVISIBLE
//               binding.counter.visibility = View.VISIBLE
//               binding.acceptChallengeButton.visibility = View.VISIBLE
//               binding.declineChallengeButton.visibility = View.VISIBLE
//           }
//
//        })

        return binding.root
    }
}
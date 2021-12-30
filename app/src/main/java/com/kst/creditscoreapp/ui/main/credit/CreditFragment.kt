package com.kst.creditscoreapp.ui.main.credit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kst.creditscoreapp.databinding.FragmentCreditBinding
import com.kst.creditscoreapp.donut.MyDonutViewAnimation
import com.kst.creditscoreapp.model.LocalScoreDetail
import com.kst.creditscoreapp.network.CreditState
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CreditFragment : DaggerFragment() {
    private var _binding: FragmentCreditBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var creditFragmentViewModel: CreditFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        lifecycleScope.launch {
            creditFragmentViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is CreditState.Success -> {
                        uiState.localScoreDetail?.apply {
                            setDonutViewScoreDetail(score, color, angle, maxScore)

                            binding.customView.setOnClickListener {
                                gotoDetailPage(this)
                            }
                        }
                    }
                    is CreditState.Error -> {
                        uiState.exception?.let { exception ->
                            Toast.makeText(
                                this@CreditFragment.activity,
                                exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                }
            }
        }

        creditFragmentViewModel.getNewData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setDonutViewScoreDetail(
        score: String,
        @ColorInt color: Int,
        newAngle: Int,
        maxScore: String
    ) {
        binding.customView.setUpScoreViewDetail(score, color, maxScore)
        binding.customView.visibility = View.VISIBLE
        val animation = MyDonutViewAnimation(binding.customView, newAngle)
        animation.duration = 5000
        binding.customView.startAnimation(animation)
    }

    private fun gotoDetailPage(localScoreDetail: LocalScoreDetail) {
        val action = CreditFragmentDirections.actionCreditFragmentToDetailFragment(localScoreDetail)
        findNavController().navigate(action)

    }

}
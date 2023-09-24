package hieudx.fpoly.livedata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hieudx.fpoly.livedata.databinding.FragmentDetailBinding
import hieudx.fpoly.livedata.databinding.FragmentSumBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var mMedalViewModel: MedalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        mMedalViewModel =
            ViewModelProvider(requireActivity()).get(MedalViewModel::class.java) //requireActivity chính là activity chứa fragment này
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMedalViewModel.gold.observe(requireActivity(), Observer<Int> { displayMedal() })
        mMedalViewModel.silver.observe(requireActivity(), Observer<Int> { displayMedal() })
        mMedalViewModel.bronze.observe(requireActivity(), Observer<Int> { displayMedal() })
    }

    private fun displayMedal() {
        binding.tvDetailGoldNumber.text =
            activity?.getString(R.string.number_of_gold_label, mMedalViewModel?.gold?.value)
        binding.tvDetailSilverNumber.text =
            activity?.getString(R.string.number_of_silver_label, mMedalViewModel?.silver?.value)
        binding.tvDetailBronzeNumber.text =
            activity?.getString(R.string.number_of_bronze_label, mMedalViewModel?.bronze?.value)
    }
}
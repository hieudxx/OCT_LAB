package hieudx.fpoly.livedata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hieudx.fpoly.livedata.databinding.FragmentControlBinding
import hieudx.fpoly.livedata.databinding.FragmentSumBinding


class SumFragment : Fragment() {
    //    frag này là observer vì nó quan tâm đến sự thay đổi của các gt, nên ta phải đky nó là 1 observer
    private lateinit var binding: FragmentSumBinding
    private lateinit var mMedalViewModel: MedalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSumBinding.inflate(inflater, container, false)
        mMedalViewModel =
            ViewModelProvider(requireActivity()).get(MedalViewModel::class.java) //requireActivity chính là activity chứa fragment này
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        quan sát sự thay đổi của các giá trị, chỉ khi nào các gt thay đổi thì mới thực hiện displayMedal
        mMedalViewModel.gold.observe(requireActivity(), Observer<Int> { displayMedal() })
        mMedalViewModel.silver.observe(requireActivity(), Observer<Int> { displayMedal() })
        mMedalViewModel.bronze.observe(requireActivity(), Observer<Int> { displayMedal() })
    }

    private fun displayMedal() {
        var totalMedal: Int =
            mMedalViewModel?.gold?.value!!.plus(mMedalViewModel?.silver?.value!!)
                .plus(mMedalViewModel?.bronze?.value!!)
        binding.tvNumberOfMedal.text =
            activity?.getString(R.string.number_of_medal_label, totalMedal)
    }
}
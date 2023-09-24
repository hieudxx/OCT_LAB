package hieudx.fpoly.livedata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import hieudx.fpoly.livedata.databinding.FragmentControlBinding

class ControlFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentControlBinding
    private lateinit var mMedalViewModel: MedalViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mMedalViewModel = ViewModelProvider(requireActivity()).get(MedalViewModel::class.java) //requireActivity chính là activity chứa fragment này
        binding = FragmentControlBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayMedal()
        binding.btnGoldMinus.setOnClickListener(this)
        binding.btnGoldPlus.setOnClickListener(this)
        binding.btnSilverMinus.setOnClickListener(this)
        binding.btnSilverPlus.setOnClickListener(this)
        binding.btnBronzeMinus.setOnClickListener(this)
        binding.btnBronzePlus.setOnClickListener(this)
    }

    private fun displayMedal() {
        binding.tvMainGoldNumber.text = mMedalViewModel.gold.value.toString()
        binding.tvMainSilverNumber.text = mMedalViewModel.silver.value.toString()
        binding.tvMainBronzeNumber.text = mMedalViewModel.bronze.value.toString()
    }


    override fun onClick(p0: View?) {
//        frag này k phải là observer vì nó k qt đến sự thay đổi của các gt mà nó chỉ làm thay đổi gt thôi
        when (p0?.id) {
            binding.btnGoldMinus.id -> {
                mMedalViewModel.gold.value = mMedalViewModel.gold.value?.minus(1)
                displayMedal()
            }
            binding.btnGoldPlus.id -> {
                mMedalViewModel.gold.value = mMedalViewModel.gold.value?.plus(1)
                displayMedal()
            }
            binding.btnSilverMinus.id -> {
                mMedalViewModel.silver.value = mMedalViewModel.silver.value?.minus(1)
                displayMedal()
            }
            binding.btnSilverPlus.id -> {
                mMedalViewModel.silver.value = mMedalViewModel.silver.value?.plus(1)
                displayMedal()
            }
            binding.btnBronzeMinus.id -> {
                mMedalViewModel.bronze.value = mMedalViewModel.bronze.value?.minus(1)
                displayMedal()
            }
            binding.btnBronzePlus.id -> {
                mMedalViewModel.bronze.value = mMedalViewModel.bronze.value?.plus(1)
                displayMedal()
            }
        }
    }
}
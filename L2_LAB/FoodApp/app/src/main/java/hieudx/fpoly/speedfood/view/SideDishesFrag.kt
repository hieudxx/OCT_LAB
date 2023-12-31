package hieudx.fpoly.speedfood.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import hieudx.fpoly.speedfood.adapter.FoodAdapter
import hieudx.fpoly.speedfood.databinding.FragmentSideDishesBinding
import hieudx.fpoly.speedfood.model.Menu
import hieudx.fpoly.speedfood.viewmodel.FoodViewModel

class SideDishesFrag : Fragment() {
    private lateinit var binding: FragmentSideDishesBinding
    private lateinit var adapter: FoodAdapter
    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSideDishesBinding.inflate(inflater, container, false)
        binding.rcv.setHasFixedSize(true)
        foodViewModel = ViewModelProvider(requireActivity()).get(FoodViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodViewModel.foods.postValue(Menu.getSideDishesList())
        foodViewModel.foods.observe(requireActivity()) {
            adapter = FoodAdapter(it,foodViewModel)
            binding.rcv.adapter = adapter
        }
    }
}
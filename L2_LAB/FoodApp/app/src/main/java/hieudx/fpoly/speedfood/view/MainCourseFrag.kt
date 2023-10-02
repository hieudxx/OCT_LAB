package hieudx.fpoly.speedfood.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import hieudx.fpoly.speedfood.adapter.FoodAdapter
import hieudx.fpoly.speedfood.databinding.FragmentMainCourseBinding
import hieudx.fpoly.speedfood.model.Food
import hieudx.fpoly.speedfood.model.Menu
import hieudx.fpoly.speedfood.viewmodel.FoodViewModel


class MainCourseFrag : Fragment() {
    private lateinit var binding: FragmentMainCourseBinding
    private lateinit var adapter: FoodAdapter
    private lateinit var foodViewModel: FoodViewModel
    private lateinit var list: ArrayList<Food>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainCourseBinding.inflate(inflater, container, false)

        binding.rcv.setHasFixedSize(true)
        foodViewModel = ViewModelProvider(requireActivity()).get(FoodViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        adapter = FoodAdapter(ArrayList(), foodViewModel)
//        binding.rcv.adapter = adapter

        foodViewModel.maiCourseList.postValue(Menu.getMainCourseList())
        Log.e("maiCourseList", "${foodViewModel.maiCourseList.value?.size}")
        foodViewModel.maiCourseList.observe(viewLifecycleOwner) {
            Log.e("maiCourseList", "${foodViewModel.maiCourseList.value?.size}")
            adapter = FoodAdapter(it,foodViewModel)
            binding.rcv.adapter = adapter
        }
    }
}
package hieudx.fpoly.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hieudx.fpoly.fragment.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    private lateinit var binding: Fragment1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment1Binding.inflate(layoutInflater)
        val view = binding.root

        binding.btnMove.setOnClickListener {
            val frag2 = Fragment2()
            parentFragmentManager.beginTransaction().replace(R.id.fragContainer, frag2).commit()
        }
        return view
    }

}
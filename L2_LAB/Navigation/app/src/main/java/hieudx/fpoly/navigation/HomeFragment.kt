package hieudx.fpoly.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions


class HomeFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.btnViewProfile).setOnClickListener {
            userViewModel.loggedIn.observe(viewLifecycleOwner) { hasLoggedIn ->
                if (hasLoggedIn.not()) {
                    findNavController().navigate(R.id.action_homeFragment_to_signInFragment)
                }
            }

            view.findViewById<Button>(R.id.btnViewProfile).setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToPriofileFragment(
                        nameArg = userViewModel.getUserName() ?: ""
                    )
                )
            }
//            cách 1: truyền data bằng bundle
            val bundle = bundleOf(
                "name" to "My Name",
            )

//            cách 2: truyền bằng safe Arg, cách này sẽ đc an toàn về kiểu dữ liệu


//            hiệu ứng chuyển frag
            val options = navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left
                    popExit = R.anim.slide_out_right
                }
            }
//            cách 1: di chuyển trực tiếp trên navigation graph
//            findNavController().navigate(R.id.priofileFragment,null,options)


//            cách 2:di chuyển bằng id của action
//            với cách này những animation ta sẽ thêm ở bên xml
//            findNavController().navigate(R.id.action_homeFragment_to_priofileFragment,bundle)


        }
        super.onViewCreated(view, savedInstanceState)
    }

}
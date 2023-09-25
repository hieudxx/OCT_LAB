package hieudx.fpoly.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument


class ProfileFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    val args: ProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_priofile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
////        cách 1: bundle
////        val name = arguments?.getString("name")
////        view.findViewById<TextView>(R.id.tvProfile).text = getString(R.string.name,"$name")
//
////        cách 2: safe arg
//        val args: ProfileFragmentArgs by navArgs()
//        val name = args.nameArg
//        view.findViewById<TextView>(R.id.tvProfile).text = getString(R.string.name, "$name")
        val name = args.nameArg
        view.findViewById<TextView>(R.id.tvProfile)?.text = getString(R.string.name, "$name")
        view.findViewById<Button>(R.id.btnLogOut).setOnClickListener {
            userViewModel.logout().observe(viewLifecycleOwner) {
                findNavController().popBackStack()
            }
        }
    }

}
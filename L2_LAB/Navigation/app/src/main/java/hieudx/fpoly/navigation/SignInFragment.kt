package hieudx.fpoly.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController


class SignInFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnLogIn).setOnClickListener {
            userViewModel.login(
                view.findViewById<EditText>(R.id.edSignInUserName).text.toString(),
                view.findViewById<EditText>(R.id.edSignInPass).text.toString()
            ).observe(viewLifecycleOwner, Observer { successful ->
                if (successful) {
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Username or password not correct",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
        view.findViewById<TextView>(R.id.tvSignUp).setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }
}
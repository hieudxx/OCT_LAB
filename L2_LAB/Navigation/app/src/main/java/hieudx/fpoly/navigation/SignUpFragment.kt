package hieudx.fpoly.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

class SignUpFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnSignUp).setOnClickListener {
            val userNameText = view.findViewById<EditText>(R.id.edSignUpUserName).text.toString()
            val passwordText = view.findViewById<EditText>(R.id.edSignUpPass).text.toString()
            userViewModel.signUp(userNameText,passwordText).observe(viewLifecycleOwner, Observer { successful ->
                if (successful){
                    findNavController().popBackStack()
                } else{
                    Toast.makeText(requireContext(), "Username or Password is not empty", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


}
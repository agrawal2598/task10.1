package com.example.foodrescueapp.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.foodrescueapp.InputUtils
import com.example.foodrescueapp.R
import com.example.foodrescueapp.data.DatabaseHelper
import com.example.foodrescueapp.data.model.User
import com.example.foodrescueapp.ui.BaseFragment
import com.google.android.material.textfield.TextInputEditText

class SignUpFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.login_label).setOnClickListener {
            navController.navigateUp()
        }
        val databaseHelper = context?.let { DatabaseHelper.getInstance(it) }
        val name = view.findViewById<TextInputEditText>(R.id.name_text_input_edittext)
        val phone = view.findViewById<TextInputEditText>(R.id.phone_text_input_edittext)
        val address = view.findViewById<TextInputEditText>(R.id.address_text_input_edittext)
        val emailAddress =
            view.findViewById<TextInputEditText>(R.id.email_address_text_input_edittext)
        val password = view.findViewById<TextInputEditText>(R.id.password_text_input_edittext)
        val confirmPassword =
            view.findViewById<TextInputEditText>(R.id.confirm_password_text_input_edittext)

        view.findViewById<Button>(R.id.sign_up_button).setOnClickListener {
            if (name.text.toString().isEmpty() ||
                address.text.toString().isEmpty() ||
                phone.text.toString().isEmpty() ||
                emailAddress.text.toString().isEmpty() ||
                confirmPassword.text.toString().isEmpty() ||
                password.text.toString().isEmpty()
            ) {
                Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!(confirmPassword.text.toString().equals(password.text.toString()))) {
                Toast.makeText(context, "Password don't match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val user = User()
            user.address = address.text.toString()
            user.name = name.text.toString()
            user.phoneNumber = phone.text.toString()
            user.emailAddress = emailAddress.text.toString()
            user.password = password.text.toString()
            val users = databaseHelper?.getUser(user.emailAddress)
            if (users != null && users.isEmpty()) {
                activity?.let { it1 -> InputUtils.hideSoftKeyboard(it1) }
                databaseHelper.insertUser(user)
                navController.navigateUp()
            } else
                Toast.makeText(context, "User already exists", Toast.LENGTH_SHORT).show()
        }
    }
}
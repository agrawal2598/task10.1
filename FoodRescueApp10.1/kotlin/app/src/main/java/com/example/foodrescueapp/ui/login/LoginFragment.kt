package com.example.foodrescueapp.ui.login

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
import com.example.foodrescueapp.ui.BaseFragment
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseHelper = context?.let { DatabaseHelper.getInstance(it) }
        view.findViewById<Button>(R.id.login_button).setOnClickListener {
            activity?.let { it1 -> InputUtils.hideSoftKeyboard(it1) }
            val emailAddress =
                view.findViewById<TextInputEditText>(R.id.email_address_text_input_edittext)
            val password = view.findViewById<TextInputEditText>(R.id.password_text_input_edittext)

            val users =
                databaseHelper?.getUser(emailAddress.text.toString(), password.text.toString())
            if (users != null && users.isNotEmpty()) {
                activity?.let { it1 -> InputUtils.hideSoftKeyboard(it1) }
                navController.navigate(R.id.action_loginFragment_to_nav_home)
            } else
                Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()

        }
        view.findViewById<TextView>(R.id.create_an_account_label).setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}
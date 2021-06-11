package com.example.foodrescueapp.ui

import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.foodrescueapp.BaseActivity

open class BaseFragment : Fragment() {

    val navController: NavController by lazy { (activity as BaseActivity).navController }

    val drawerLayout: DrawerLayout by lazy { (activity as BaseActivity).drawerLayout }
}
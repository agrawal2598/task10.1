package com.google.android.gms.samples.wallet.ui

import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.android.gms.samples.wallet.BaseActivity

public open class BaseFragment : Fragment() {

    val navController: NavController by lazy { (activity as BaseActivity).navController }

    val drawerLayout: DrawerLayout by lazy { (activity as BaseActivity).drawerLayout }
}
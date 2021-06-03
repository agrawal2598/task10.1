package com.google.android.gms.samples.wallet

import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController

open class BaseActivity : AppCompatActivity() {

    lateinit var navController: NavController

    lateinit var drawerLayout: DrawerLayout
}
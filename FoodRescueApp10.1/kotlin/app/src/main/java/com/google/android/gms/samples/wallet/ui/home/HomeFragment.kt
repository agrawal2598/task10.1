package com.google.android.gms.samples.wallet.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.samples.wallet.R
import com.google.android.gms.samples.wallet.adapter.FoodAdapter
import com.google.android.gms.samples.wallet.data.DatabaseHelper
import com.google.android.gms.samples.wallet.data.model.Food
import com.google.android.gms.samples.wallet.interfaces.OnClickListener
import com.google.android.gms.samples.wallet.ui.BaseFragment
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseHelper = context?.let { DatabaseHelper.getInstance(it) }
        val foodNotFoundLabel = view.findViewById<TextView>(R.id.no_food_found_label)
        val foodRecyclerView = view.findViewById<RecyclerView>(R.id.food_recycler_view)
        val food = databaseHelper?.getFood()
        foodRecyclerView.adapter = food?.let {
            FoodAdapter(it, object : OnClickListener {
                override fun onClick(food: Food) {
                    databaseHelper.updateFood(food)
                }

            }, object : OnClickListener {
                override fun onClick(food: Food) {
                    Food.cartList.add(food)
                    Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
                }
            })
        }
        view.findViewById<FloatingActionButton>(R.id.add_fab).setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_addFoodFragment)
        }
        view.findViewById<FloatingActionButton>(R.id.cart).setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_myCartFragment)
        }
        view.findViewById<ExtendedFloatingActionButton>(R.id.my_list_fab).setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_myListFragment)
        }
        view.findViewById<ImageView>(R.id.logout_image_view).setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_loginFragment)
        }
        if (food?.isNullOrEmpty() == true)
            foodNotFoundLabel.visibility = View.VISIBLE
    }
}
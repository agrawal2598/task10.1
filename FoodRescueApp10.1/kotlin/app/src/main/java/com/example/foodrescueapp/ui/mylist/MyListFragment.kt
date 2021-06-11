package com.example.foodrescueapp.ui.mylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrescueapp.R
import com.example.foodrescueapp.adapter.FoodAdapter
import com.example.foodrescueapp.data.DatabaseHelper
import com.example.foodrescueapp.data.model.Food
import com.example.foodrescueapp.interfaces.OnClickListener
import com.example.foodrescueapp.ui.BaseFragment

class MyListFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseHelper = context?.let { DatabaseHelper.getInstance(it) }
        val foodRecyclerView = view.findViewById<RecyclerView>(R.id.food_recycler_view)
        val food = databaseHelper?.getFood(0)
        val adapter = food?.let {
            FoodAdapter(
                it,
                param = null,
                onCLickCart = null,
                param1 = object : OnClickListener {
                    override fun onClick(food: Food) {
                        Food.food = food
                        navController.navigate(R.id.action_nav_home_to_viewFoodFragment)
                    }
                }
            )
        }
        foodRecyclerView.adapter = adapter
    }
}
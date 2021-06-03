package com.google.android.gms.samples.wallet.ui.mylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.samples.wallet.R
import com.google.android.gms.samples.wallet.adapter.FoodAdapter
import com.google.android.gms.samples.wallet.data.DatabaseHelper
import com.google.android.gms.samples.wallet.data.model.Food
import com.google.android.gms.samples.wallet.interfaces.OnClickListener

class MyListFragment : Fragment() {

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
        foodRecyclerView.adapter = food?.let { FoodAdapter(it) }
    }
}
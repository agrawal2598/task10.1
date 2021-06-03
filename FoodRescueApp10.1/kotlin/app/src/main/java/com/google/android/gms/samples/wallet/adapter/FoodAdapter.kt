package com.google.android.gms.samples.wallet.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.samples.wallet.R
import com.google.android.gms.samples.wallet.data.model.Food
import com.google.android.gms.samples.wallet.interfaces.OnClickListener

class FoodAdapter(val food: List<Food>, val param: OnClickListener? = null, val onCLickCart: OnClickListener? = null) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleLabel: TextView = itemView.findViewById(R.id.title_label)
        val descriptionLabel: TextView = itemView.findViewById(R.id.description_label)
        val imageView: ImageView = itemView.findViewById(R.id.food_image_view)
        val shareImageView: ImageView = itemView.findViewById(R.id.share_image_view)
        val cartImageView: ImageView = itemView.findViewById(R.id.cart_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = food[position]
        holder.titleLabel.text = item.title
        holder.descriptionLabel.text = item.description

        if (item.path.isNotEmpty())
            try {
                Glide.with(holder.imageView)
                    .load(Uri.parse(item.path))
                    .into(holder.imageView)
            } catch (e: Exception) {
            }

        holder.shareImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND);
            val shareBody = "${item.title}\n${item.description}";
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
            startActivity(it.context, Intent.createChooser(intent, "Share Using"), null)

            param?.onClick(item)
        }

        holder.cartImageView.setOnClickListener {
            onCLickCart?.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return food.size
    }
}
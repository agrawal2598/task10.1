package com.google.android.gms.samples.wallet.ui.food

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.model.Image
import com.google.android.gms.samples.wallet.R
import com.google.android.gms.samples.wallet.data.DatabaseHelper
import com.google.android.gms.samples.wallet.data.model.Food
import com.google.android.gms.samples.wallet.ui.BaseFragment
import com.google.android.material.textfield.TextInputEditText


class AddFoodFragment : BaseFragment() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            images = ImagePicker.getImages(data) as ArrayList<Image?>

            imageView?.let {
                if (images.isNotEmpty())
                    Glide.with(it)
                        .load(images[0]?.uri)
                        .into(it)
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val databaseHelper = context?.let { DatabaseHelper.getInstance(it) }
        val title = view.findViewById<TextInputEditText>(R.id.title_text_input_edittext)
        imageView = view.findViewById<ImageView>(R.id.camera_image_view)
        val description = view.findViewById<TextInputEditText>(R.id.description_text_input_edittext)
        val quantity = view.findViewById<TextInputEditText>(R.id.quantity_text_input_edittext)
        val location = view.findViewById<TextInputEditText>(R.id.location_text_input_edittext)
        val date = view.findViewById<CalendarView>(R.id.date_calendar_view)
        val time = view.findViewById<TimePicker>(R.id.pick_up_time_picker)

        view.findViewById<ConstraintLayout>(R.id.add_image_container).setOnClickListener {
            ImagePicker.create(this)
                .single().start()
        }
        view.findViewById<Button>(R.id.save_button).setOnClickListener {
            if (title.text.toString().isEmpty() ||
                description.text.toString().isEmpty() ||
                quantity.text.toString().isEmpty() ||
                location.text.toString().isEmpty()
            ) {
                Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val food = Food()
            food.time = time.toString()
            food.date = date.date.toString()
            food.title = title.text.toString()
            food.description = description.text.toString()
            food.quantity = quantity.text.toString()
            food.location = location.text.toString()

            if (images.isNotEmpty())
                food.path = images[0]?.uri.toString()
            else
                food.path = ""


            databaseHelper?.insertFood(food)

            navController.navigateUp()
        }
    }

    private var imageView: ImageView? = null
    private var images = arrayListOf<Image?>()
}
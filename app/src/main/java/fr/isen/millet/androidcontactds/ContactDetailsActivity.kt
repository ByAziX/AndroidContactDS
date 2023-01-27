package fr.isen.millet.androidcontactds

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.millet.androidcontactds.databinding.ActivityContactDetailsBinding
import fr.isen.millet.androidcontactds.modelData.Results

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val results = intent.extras?.get("results") as Results

        binding.nameContactDetailsView.text = results.name?.title + " " + results.name?.first + " " + results.name?.last

        // picasso
        Picasso.get().load(results.picture?.large).into(binding.imageContactdetailsView)


    }

}
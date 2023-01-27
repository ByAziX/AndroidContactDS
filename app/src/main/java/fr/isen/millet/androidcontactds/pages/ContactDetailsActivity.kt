package fr.isen.millet.androidcontactds.pages

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
        val phone = results.phone?.split("-")?.joinToString(" ")

        binding.nameContactDetailsView.text = results.name?.title + " " + results.name?.first + " " + results.name?.last
        binding.emailContactdetailsView.text = results.email
        binding.phoneContactDetailsView.text = ""
        binding.phoneContactDetailsView.text = phone
        binding.locationContactDetailsView.text =  results.location?.street?.name + " " + results.location?.street?.number + ", " + results.location?.city + ", " + results.location?.state + ", " + results.location?.country + ", " + results.location?.postcode
        binding.dateContactDetailsView.text = results.dob?.date?.substring(8, 10) + "/" + results.dob?.date?.substring(5, 7) + "/" + results.dob?.date?.substring(0, 4)

        // picasso
        Picasso.get().load(results.picture?.large).into(binding.imageContactdetailsView)




    }


}
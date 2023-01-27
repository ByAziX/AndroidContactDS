package fr.isen.millet.androidcontactds

import CustomAdapter
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.millet.androidcontactds.databinding.ActivityContactBinding
import fr.isen.millet.androidcontactds.modelData.Data
import org.json.JSONObject

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private var url = "https://randomuser.me/api/?results=10&nat=fr"
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)


            binding.pBar.visibility = View.VISIBLE
            binding.TitleContact.text = "Affichage des conatcts"

            title = binding.TitleContact.text


            val recyclerView = binding.recyclerview
            recyclerView.layoutManager = LinearLayoutManager(this)

            recyclerView.adapter = CustomAdapter(arrayListOf()) {

                val intent = Intent(this, ContactDetailsActivity::class.java)
                intent.putExtra("results", it)

                startActivity(intent)

            }
            val json = JSONObject()
            json.put("results", 10)

            val request = JsonObjectRequest(
                Request.Method.GET, url, json,
                {
                    Log.d("TAG2", it.toString())


                    val gson = Gson()
                    // get the data from the json object and put it in the data class object (data)
                    val data: Data = gson.fromJson(it.toString(), Data::class.java)
                    // get the list of dishes from the data class object
                    val list = data.results
                    Log.d("TAG3", list.toString())


                    //val list: Results = gson.fromJson(it.toString(), Results::class.java)
                   // Log.d("TAG", list.toString())

                    //val filterList = list.data.firstOrNull() { it.name_fr == binding.TitleCategorie.text }

                    binding.pBar.visibility = View.GONE

                    val adapter = binding.recyclerview.adapter as CustomAdapter
                   if (list != null) {
                          adapter.refreshList(list)
                    }

                    //update list



                },
                {

                    Log.e("API Error", it.toString())
                })


            Volley.newRequestQueue(this).add(request)
        }
        override fun onDestroy() {
            super.onDestroy()
            Log.d ("onDestroy", "$this onDestroy")
        }
    }

package fr.isen.millet.androidcontactds.pages

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
    var countPage = 1
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)


            binding.pBar.visibility = View.VISIBLE
            binding.TitleContact.text = "Affichage des conatcts"

            title = binding.TitleContact.text

            getApiContact()

        binding.buttonPageSuivante.setOnClickListener {
            countPage++
            pageSuivante()
            binding.buttonPagePrecedente.isEnabled = true


        }

            binding.buttonPagePrecedente.setOnClickListener {
                if (countPage > 1) {
                    countPage--
                    pageSuivante()
                } else {
                    binding.buttonPagePrecedente.isEnabled = false
                }



            }

            val recyclerView = binding.recyclerview
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = CustomAdapter(arrayListOf()) {

                val intent = Intent(this, ContactDetailsActivity::class.java)
                intent.putExtra("results", it)

                startActivity(intent)

            }

        }
        override fun onDestroy() {
            super.onDestroy()
            Log.d ("onDestroy", "$this onDestroy")
        }

    fun pageSuivante() {

            url = "https://randomuser.me/api/?results=10&nat=fr&page=" + countPage
            Log.d("URL", countPage.toString())
            binding.pageView.text = countPage.toString()
            getApiContact()

    }


    // function wich get the api and parse it to the adapter to display it in the recyclerview
    private fun getApiContact(){
        val json = JSONObject()
        json.put("results", 10)

        val request = JsonObjectRequest(
            Request.Method.GET, url, json,
            {
                Log.d("TAG2", it.toString())


                val gson = Gson()
                val data: Data = gson.fromJson(it.toString(), Data::class.java)
                val list = data.results
                Log.d("TAG3", list.toString())

                binding.pBar.visibility = View.GONE

                val adapter = binding.recyclerview.adapter as CustomAdapter
                if (list != null) {
                    adapter.refreshList(list)
                }
            },
            {

                Log.e("API Error", it.toString())
            })


        Volley.newRequestQueue(this).add(request)
    }


    }

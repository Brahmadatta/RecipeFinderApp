package com.example.recipefinderapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.recipefinderapp.R
import com.example.recipefinderapp.model.Recipe
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        search_button.setOnClickListener {

            var intent = Intent(this,RecipeList::class.java)
            var ingredients = ingredientsEdt.text.toString().trim()
            var serachTerm = searchtermEdt.text.toString().trim()

            intent.putExtra("ingredients",ingredients)
            intent.putExtra("searchTerm",serachTerm)
            startActivity(Intent(intent))

        }


    }


}
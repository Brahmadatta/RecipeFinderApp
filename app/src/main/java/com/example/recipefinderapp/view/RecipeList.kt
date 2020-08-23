package com.example.recipefinderapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.recipefinderapp.R
import com.example.recipefinderapp.data.RecipeListAdapter
import com.example.recipefinderapp.model.LEFT_LINK
import com.example.recipefinderapp.model.QUERY
import com.example.recipefinderapp.model.Recipe
import kotlinx.android.synthetic.main.activity_recipe_list.*
import org.json.JSONException
import org.json.JSONObject

class RecipeList : AppCompatActivity() {

    var volleyRequest : RequestQueue?= null

    var recipeList : ArrayList<Recipe> ?= null

    var recipeListAdapter : RecipeListAdapter ?= null
    var layoutManager : RecyclerView.LayoutManager ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)


        recipeList = ArrayList<Recipe>()

        var urlString = "http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3"

        var url : String?

        var extras = intent.extras

        var ingredients = extras!!.get("ingredients")
        var search = extras!!.get("searchTerm")

        if (extras != null && !ingredients!!.equals("") && !search!!.equals(""))
        {

            var tempUrl = LEFT_LINK + ingredients + QUERY + search

            url = tempUrl
        }else{
            url = urlString
        }

        volleyRequest = Volley.newRequestQueue(this)

        getRecipe(url)
    }

    fun getRecipe(url : String)
    {
        val recipeRequest = JsonObjectRequest(Request.Method.GET,url,null, Response.Listener{
                response: JSONObject ->
            try {

                val resultArray = response.getJSONArray("results")

                for (i in 0..resultArray.length() - 1)
                {
                    var recipeObj = resultArray.getJSONObject(i)
                    var title = recipeObj.getString("title")
                    var link = recipeObj.getString("href")
                    var ingredients = recipeObj.getString("ingredients")
                    var thumbnail = recipeObj.getString("thumbnail")


                    var recipe = Recipe()

                    recipe.title = title
                    recipe.thumbnail = thumbnail
                    recipe.ingredients = " Ingredients : ${ingredients}"
                    recipe.link = link

                    Log.e("result-->",title)

                    recipeList!!.add(recipe)

                    recipeListAdapter = RecipeListAdapter(recipeList!!,this)
                    layoutManager = LinearLayoutManager(this)

                    //set up recyclerview

                    recyclerView.layoutManager = layoutManager
                    recyclerView.adapter = recipeListAdapter

                }

                recipeListAdapter!!.notifyDataSetChanged()


            }catch (e: JSONException)
            {
                e.printStackTrace()
            }
        }, Response.ErrorListener {
                error ->
            try {

                Log.e("error",error.toString())
            }catch (e : JSONException)
            {
                e.printStackTrace()
            }
        })

        volleyRequest!!.add(recipeRequest)
    }
}
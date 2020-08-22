package com.example.recipefinderapp.data

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipefinderapp.R
import com.example.recipefinderapp.model.Recipe
import com.squareup.picasso.Picasso

class RecipeListAdapter(private val list: ArrayList<Recipe>,
                        private val context: Context) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_row_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (holder != null)
        {
            holder.bindView(list[position])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var title = itemView.findViewById(R.id.recipeTitle) as TextView
        var image = itemView.findViewById(R.id.image) as ImageView
        var link = itemView.findViewById(R.id.linkButton) as Button
        var ingred = itemView.findViewById(R.id.ingredient) as TextView

        fun bindView(recipe: Recipe)
        {
            title.text = recipe.title
            ingred.text = recipe.ingredients

            if (!TextUtils.isEmpty(recipe.thumbnail)) {

                    Picasso.get()
                        .load(recipe.thumbnail)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(image)
                }else{
                Picasso.get().load(R.drawable.ic_launcher_background).into(image)
            }
        }
    }
}

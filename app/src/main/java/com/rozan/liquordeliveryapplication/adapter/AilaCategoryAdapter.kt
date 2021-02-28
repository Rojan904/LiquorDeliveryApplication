package com.rozan.liquordeliveryapplication.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rozan.liquordeliveryapplication.R
import com.rozan.liquordeliveryapplication.model.AilaCategory

class AilaCategoryAdapter(
    val lstCategory:ArrayList<AilaCategory>,
    val context: Context
):RecyclerView.Adapter<AilaCategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(view: View):RecyclerView.ViewHolder(view){
        val imgCateg: ImageView
        val ailaCateg:TextView

        init {
            imgCateg=view.findViewById(R.id.imgCateg)
            ailaCateg=view.findViewById(R.id.ailaCateg)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.aila_category_layout,parent,false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            val category=lstCategory[position]
            holder.ailaCateg.text=category.categName

            Glide.with(context)
                .load(category.categImage)
                .into(holder.imgCateg)

        holder.imgCateg.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
    return lstCategory.size
    }


}

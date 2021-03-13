package com.rozan.liquordeliveryapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rozan.liquordeliveryapplication.AilaDetailsActivity
import com.rozan.liquordeliveryapplication.R
import com.rozan.liquordeliveryapplication.api.ServiceBuilder
import com.rozan.liquordeliveryapplication.entity.Aila

class AilaAdapter (
        val lstAila:MutableList<Aila>,
        val context: Context
        ):RecyclerView.Adapter<AilaAdapter.AilaViewHolder>()
{
    class AilaViewHolder(view: View):RecyclerView.ViewHolder(view){
        val ailaImage:ImageView
        val ailaPrice:TextView
        val ailaMl:TextView
        val ailaName:TextView
        val ailaType:TextView
        val imgFavorite:ImageView
        val imgCart:ImageView

        init {
            ailaImage=view.findViewById(R.id.image)
            ailaPrice=view.findViewById(R.id.price)
            ailaMl=view.findViewById(R.id.ml)
            ailaName=view.findViewById(R.id.name)
            ailaType=view.findViewById(R.id.type)
            imgFavorite=view.findViewById(R.id.imgFavorite)
            imgCart=view.findViewById(R.id.imgCart)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AilaViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.show_aila_layout,parent,false)
        return AilaViewHolder(view)
    }

    override fun onBindViewHolder(holder: AilaViewHolder, position: Int) {
        val aila=lstAila[position]
        holder.ailaPrice.text=aila.ailaPrice.toString()
        holder.ailaMl.text=aila.ailaMl.toString()
        holder.ailaName.text=aila.ailaName.toString()
        holder.ailaType.text=aila.ailaType.toString()

        val imagePath = ServiceBuilder.loadImagePath() + aila.ailaImage!!.split("\\")[1]
        Glide.with(context)
                .load(imagePath)
                .into(holder.ailaImage)
//        Glide.with(context)
//            .load(aila.ailaImage)
//            .into(holder.ailaImage)

        holder.itemView.setOnClickListener {
            val intent= Intent(context,AilaDetailsActivity::class.java)
            intent.putExtra("aila",aila)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return lstAila.size
    }
}
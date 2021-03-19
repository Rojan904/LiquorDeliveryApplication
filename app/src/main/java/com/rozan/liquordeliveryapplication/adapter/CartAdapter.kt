package com.rozan.liquordeliveryapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rozan.liquordeliveryapplication.R
import com.rozan.liquordeliveryapplication.api.ServiceBuilder
import com.rozan.liquordeliveryapplication.entity.Cart

class CartAdapter(
        val lstcart:MutableList<Cart>,
        val context: Context
):RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    class CartViewHolder(view: View):RecyclerView.ViewHolder(view){
        val ailaImage:ImageView
        val ailaPrice: TextView
        val ailaMl: TextView
        val ailaName: TextView
        val ailaQty:TextView
        val btnDelete:ImageView

        init {
            ailaImage=view.findViewById(R.id.imgProfile)
            ailaPrice=view.findViewById(R.id.ailaPrice)
            ailaMl=view.findViewById(R.id.ailaMl)
            ailaName=view.findViewById(R.id.ailaName)
            ailaQty=view.findViewById(R.id.ailaQty)
            btnDelete=view.findViewById(R.id.btnDelete)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.cart_layout,parent,false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart=lstcart[position]
        holder.ailaPrice.text=cart.ailaPrice.toString()
        holder.ailaMl.text=cart.ailaMl
        holder.ailaName.text=cart.ailaName
        holder.ailaQty.text=cart.ailaQty.toString()

        val imagePath = ServiceBuilder.loadImagePath() + cart.ailaImage!!.split("\\")[1]
        Glide.with(context)
                .load(imagePath)
//                .apply(RequestOptions.skipMemoryCacheOf(true))
//                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(holder.ailaImage)
    }

    override fun getItemCount(): Int {
       return lstcart.size
    }
}
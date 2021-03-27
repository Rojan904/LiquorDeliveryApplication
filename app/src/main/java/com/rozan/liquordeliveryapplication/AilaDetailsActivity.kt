package com.rozan.liquordeliveryapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.BinderThread
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.rozan.liquordeliveryapplication.api.ServiceBuilder
import com.rozan.liquordeliveryapplication.entity.Aila
import com.rozan.liquordeliveryapplication.entity.Cart
import com.rozan.liquordeliveryapplication.repository.CartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AilaDetailsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var imgAila:ImageView
    private lateinit var tvName:TextView
    private lateinit var tvType:TextView
    private lateinit var tvMl:TextView
    private lateinit var tvPrice:TextView
    private lateinit var tvDes:TextView
    private lateinit var tvDescrip:TextView
    private lateinit var btnSub:Button
    private lateinit var btnAdd: Button
    private lateinit var ailaQty: TextView
    private lateinit var btnAddToCart: Button

    var counter=1
    private var ailaImage:String?=null
    private  var ailaName:String?=null
    private var ailaType:String?=null

    private var ailaMl:String?=null
    private var ailaPrice:Double?=0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aila_details)

        imgAila=findViewById(R.id.imgAila)
        tvName=findViewById(R.id.ailaName)
        tvType=findViewById(R.id.ailaType)
        tvMl=findViewById(R.id.ailaMl)
        tvPrice=findViewById(R.id.ailaPrice)
        tvDes=findViewById(R.id.ailaDes)
        tvDescrip=findViewById(R.id.ailaDescrip)
        btnSub=findViewById(R.id.btnSub)
        btnAdd=findViewById(R.id.btnAdd)
        ailaQty=findViewById(R.id.ailaQty)
        btnAddToCart=findViewById(R.id.btnAddCart)


        val intent=intent.getParcelableExtra<Aila>("aila")
        if (intent!=null){
             ailaName=intent.ailaName
             ailaType=intent.ailaType
             ailaMl=intent.ailaMl
             ailaPrice=intent.ailaPrice
            ailaImage=intent.ailaImage.toString()
            tvName.text=ailaName
            tvType.text=ailaType
            tvMl.text=ailaMl.toString()
            tvPrice.text=ailaPrice.toString()

            val imagePath = ServiceBuilder.loadImagePath() + intent.ailaImage!!.split("\\")[1]
            Glide.with(this)
                    .load(imagePath)
                    .into(imgAila)



        }
        btnAdd.setOnClickListener(this)
        btnSub.setOnClickListener(this)
        btnAddToCart.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnAdd->{
                counter++
                ailaQty.text=counter.toString()
                if(counter>1){
                    btnSub.isClickable=true
                }

            }
            R.id.btnSub->{
                counter--
                ailaQty.text=counter.toString()
                if(counter<=1){
                    btnSub.isClickable=false
                }


            }
            R.id.btnAddCart -> {

               val ailaQty=ailaQty.text.toString().toInt()

                val cart=Cart(ailaImage=ailaImage,ailaPrice = ailaPrice,ailaMl = ailaMl,ailaName = ailaName,ailaQty = ailaQty)

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val cartRepository= CartRepository()
                        val response=cartRepository.addToCart(cart)
                        if (response.success==true){

                            withContext(Dispatchers.Main){
                                Toast.makeText(this@AilaDetailsActivity, "Product added to cart", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    catch (ex: Exception){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                    this@AilaDetailsActivity,
                                    ex.toString(),
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
        }
    }
}
package com.rozan.liquordeliveryapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.BinderThread
import com.rozan.liquordeliveryapplication.entity.Aila

class AilaDetailsActivity : AppCompatActivity() {
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
            val ailaName=intent.ailaName
            val ailaType=intent.ailaType
            val ailaMl=intent.ailaMl
            val ailaPrice=intent.ailaPrice

            tvName.text=ailaName
            tvType.text=ailaType
            tvMl.text=ailaMl.toString()
            tvPrice.text=ailaPrice.toString()
            tvDescrip.text="mnajasdhlasdjlkasjdlkasjdlksjdklasjdlkasjdlksajdklasjdsakljdddddddskdjaslkdjlsakjdskajdlska" +
                    "mnajasdhlasdjlkasjdlkasjdlksjdklasjdlkasjdlksajdklasjdsakljdddddddskdjaslkdjlsakjdskajdlskdjhlskadjlksadjlakjdaklsjdkasjdklsajdklasjdklsadjklsjdklsajdakskldjaskldjaskldj"


        }

    }
}
package com.rozan.liquordeliveryapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rozan.liquordeliveryapplication.dao.CustomerDAO
import com.rozan.liquordeliveryapplication.entity.Customer

@Database(
    entities =[Customer::class],
    version = 1,
    exportSchema = false
)
abstract class CustomerDB:RoomDatabase() {
    abstract fun getCustomerDAO():CustomerDAO  //instance of CustomerDAO interface to access the function
    companion object{
        @Volatile
        private var instance:CustomerDB?=null
        fun getInstance(context: Context):CustomerDB{
            if (instance==null){
                synchronized(CustomerDB::class){
                    instance=buildDatabase(context)
                }
            }
            return instance!!
        }

        //function for building database
        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                CustomerDB::class.java,
                "AilaDB"
            ).build()
    }
}
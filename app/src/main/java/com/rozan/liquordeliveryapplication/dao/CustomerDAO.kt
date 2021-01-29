package com.rozan.liquordeliveryapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rozan.liquordeliveryapplication.entity.Customer

@Dao
interface CustomerDAO {
    @Insert  //Inserting into database
    suspend fun registerCustomer(customer: Customer)

    @Query("select * from Customer where username=(:username) and password=(:password)")
    suspend fun checkCustomer(username:String,password:String):Customer


}
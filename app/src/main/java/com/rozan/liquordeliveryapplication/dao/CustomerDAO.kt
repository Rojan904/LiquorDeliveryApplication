package com.rozan.liquordeliveryapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import com.rozan.liquordeliveryapplication.entity.Customer

@Dao
interface CustomerDAO {
    @Insert  //Inserting into database
    suspend fun registerCustomer(customer: Customer)

}
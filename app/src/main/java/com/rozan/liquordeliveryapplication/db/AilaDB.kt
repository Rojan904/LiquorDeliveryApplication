package com.rozan.liquordeliveryapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rozan.liquordeliveryapplication.dao.UserDAO
import com.rozan.liquordeliveryapplication.entity.User

@Database(
        entities =[User::class],
        version = 1,
        exportSchema = false
)
abstract class AilaDB:RoomDatabase() {
    abstract fun getUserDAO():UserDAO  //instance of UserDAO interface to access the function
    companion object{
        @Volatile
        private var instance:AilaDB?=null
        fun getInstance(context: Context):AilaDB{
            if (instance==null){
                synchronized(AilaDB::class){
                    instance=buildDatabase(context)
                }
            }
            return instance!!
        }

        //function for building database
        private fun buildDatabase(context: Context)=
                Room.databaseBuilder(
                        context.applicationContext,
                        AilaDB::class.java,
                        "AilaDB"
                ).build()
    }
}
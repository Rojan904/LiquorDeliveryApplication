package com.rozan.liquordeliveryapplication

import com.rozan.liquordeliveryapplication.entity.Users
import com.rozan.liquordeliveryapplication.repository.AilaRepository
import com.rozan.liquordeliveryapplication.repository.CartRepository
import com.rozan.liquordeliveryapplication.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UnitTesting {
    private lateinit var userRepository: UserRepository
    private lateinit var cartRepository: CartRepository
    private lateinit var ailaRepository: AilaRepository

    @Test
    fun checkSignUp()= runBlocking {
        val user= Users(firstName = "Salina",
            lastName = "Shrestha",
            dob = "10-2-2000",
            username = "salina10",
            email = "salina@gmail.com",
            password = "salina12")
        userRepository= UserRepository()
        val response=userRepository.registerUser(user)
        val expectedResult=true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    @Test
    fun checkLogin()= runBlocking {
        userRepository= UserRepository()
        val response=userRepository.checkUser("salina10","salina12")
        val expectedResult=true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

}
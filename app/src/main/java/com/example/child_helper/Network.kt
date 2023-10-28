package com.example.child_helper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Network {
    val retrofitInstance: BioPassIDApi
        /** Returns a Client Retrofit Instance for Requests
         */
        get() = Retrofit.Builder()
            .baseUrl("https://api.biopassid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BioPassIDApi::class.java)
}
package com.example.mycasino2.api

import com.example.mycasino2.view.model.Message
import com.example.mycasino2.view.model.ResponceWebView
import retrofit2.Response

class Repository {

    suspend fun setParametersPhone(phone_name:String,locale:String,unique:String): Response<ResponceWebView> {
        return RetrofitInstance.api.setPostParametersPhone(phone_name, locale, unique)
    }

    suspend fun getTextInTutorial(): Response<Message> {
        return RetrofitInstance.api.getTextInTutorial()
    }

}
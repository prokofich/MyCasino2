package com.example.mycasino2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino2.api.Repository
import com.example.mycasino2.view.model.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class TotorialViewModel:ViewModel() {

    val repo = Repository()
    val Message: MutableLiveData<Response<Message>> = MutableLiveData()

    fun getTextInTutorial() {
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repo.getTextInTutorial()
            withContext(Dispatchers.Main) {
                Message.value = responce
            }
        }
    }

}
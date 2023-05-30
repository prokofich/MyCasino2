package com.example.mycasino2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.onesignal.OneSignal

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        MAIN = this
        navController = Navigation.findNavController(this,R.id.id_nav_host)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }


    fun getRecordHard(): Int {
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(RECORD_HARD, 0)
    }
    fun getRecordMedium(): Int {
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(RECORD_MEDIUM, 0)
    }
    fun getRecordEasy(): Int {
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(RECORD_EASY, 0)
    }
    fun getMyCash():Int{
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(MY_CASH,0)
    }
    fun getLastDay():String{
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE).getString(LAST_DAY,"")
        return preferences ?: ""
    }
    fun getMyName():String{
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE).getString(MY_NAME,"")
        return preferences ?: ""
    }



    fun setMyName(name:String){
        val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        preferences.edit()
            .putString(MY_NAME,name)
            .apply()
    }
    fun setRecordHard(record:Int){
        val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        preferences.edit()
            .putInt(RECORD_HARD,record)
            .apply()
    }
    fun setRecordMedium(record:Int){
        val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        preferences.edit()
            .putInt(RECORD_MEDIUM,record)
            .apply()
    }
    fun setRecordEasy(record:Int){
        val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        preferences.edit()
            .putInt(RECORD_EASY,record)
            .apply()
    }
    fun setCurrentDay(day:String){
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        preferences.edit()
            .putString(LAST_DAY,day)
            .apply()
    }
    fun addCash(cash:Int){
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE).getInt(MY_CASH,0) + cash
        val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        pref.edit()
            .putInt(MY_CASH,preferences)
            .apply()
    }
    fun minusCash(cash: Int){
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE).getInt(MY_CASH,0) - cash
        val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        pref.edit()
            .putInt(MY_CASH,preferences)
            .apply()
    }





}
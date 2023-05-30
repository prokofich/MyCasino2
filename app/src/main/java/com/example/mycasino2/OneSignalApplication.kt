package com.example.mycasino2

import android.app.Application
import com.onesignal.OneSignal

class OneSignalApplication:Application() {

    val ONESIGNAL_APP_ID = "491fd8ba-ed37-4ba8-a112-940dbc55e09e"

    override fun onCreate() {
        super.onCreate()

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

    }

}
package com.mobileapp.staffgridcompose.application

import android.app.Application
import com.a71cities.example.paypayexchange.utils.Pref

class StaffGridApp: Application() {

    companion object {
        lateinit var instance: StaffGridApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Pref.init(this)
    }
}
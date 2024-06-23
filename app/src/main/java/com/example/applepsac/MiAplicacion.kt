package com.example.applepsac

import android.app.Application
import com.google.firebase.FirebaseApp

class MiAplicacion : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
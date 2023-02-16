package com.example.myapplication

import android.app.Application;
import com.example.myapplication.flutter.FlutterEngineEntrypoint
import com.example.myapplication.flutter.FlutterEngineHandler

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        FlutterEngineHandler.initialize(applicationContext)
        FlutterEngineHandler.createOrGetEngine(applicationContext, FlutterEngineEntrypoint.Main)
    }
}
package com.example.myapplication.flutter

enum class FlutterEngineEntrypoint(
    val engineId: String,
    val entrypoint: String,
    val pathToBundle: String = "lib/main.dart")
{
    Main("MAIN", "main"),
}
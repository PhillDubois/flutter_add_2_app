package com.example.myapplication.flutter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterFragment

class CBFlutterActivity: AppCompatActivity()
{
    companion object
    {
        private const val FLUTTER_FRAGMENT_TAG = "flutter_page_fragment"
    }

    private var flutterFragment: FlutterFragment? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val root = FlutterEngineHandler.createRootView(this)
        setContentView(root)

        flutterFragment = FlutterEngineHandler.attachFlutterFragment(this, root, supportFragmentManager, FLUTTER_FRAGMENT_TAG, FlutterEngineEntrypoint.Main)

    }

    override fun onNewIntent(intent: Intent?)
    {
        if (intent != null)
        {
            flutterFragment?.onNewIntent(intent)
        }
        super.onNewIntent(intent)
    }

    override fun onBackPressed()
    {
        super.onBackPressed()
        flutterFragment?.onBackPressed()
    }

    override fun onUserLeaveHint()
    {
        super.onUserLeaveHint()
        flutterFragment?.onUserLeaveHint()
    }

    override fun onDestroy()
    {
        flutterFragment?.detachFromFlutterEngine()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        flutterFragment?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}
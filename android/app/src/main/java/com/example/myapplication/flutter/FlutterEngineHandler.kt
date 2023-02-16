package com.example.myapplication.flutter

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.RenderMode
import io.flutter.embedding.android.TransparencyMode
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.embedding.engine.dart.DartExecutor

class FlutterEngineHandler
{
    companion object
    {

        private const val LOG_TAG = "FlutterEngineHandler"

        private lateinit var engineGroup: FlutterEngineGroup

        fun initialize(context: Context)
        {
            engineGroup = FlutterEngineGroup(context)
        }

        fun createOrGetEngine(
            context: Context,
            engineEntrypoint: FlutterEngineEntrypoint): FlutterEngine
        {
            var cachedEngine = getEngine(engineEntrypoint)
            if (cachedEngine == null)
            {
                Log.d(LOG_TAG, "Engine ${engineEntrypoint.engineId} does not exist, creating engine")

                val entrypoint = DartExecutor.DartEntrypoint(engineEntrypoint.pathToBundle, engineEntrypoint.entrypoint)
                cachedEngine = engineGroup.createAndRunEngine(context, entrypoint)
                FlutterEngineCache.getInstance().put(engineEntrypoint.engineId, cachedEngine)
            }
            return cachedEngine!!
        }

        fun getEngine(engineEntrypoint: FlutterEngineEntrypoint): FlutterEngine?
        {
            return FlutterEngineCache.getInstance().get(engineEntrypoint.engineId)
        }

        fun destroyEngine(engineEntrypoint: FlutterEngineEntrypoint)
        {
            getEngine(engineEntrypoint)?.let {
                FlutterEngineCache.getInstance().remove(engineEntrypoint.engineId)
                it.destroy()
                Log.d(LOG_TAG, "Destroyed engine ${engineEntrypoint.engineId}")
            }
        }

        fun createRootView(context: Context): ViewGroup
        {
            val view = LinearLayout(context)
            view.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            view.orientation = LinearLayout.VERTICAL
            return view
        }

        fun attachFlutterFragment(
            context: Context,
            root: ViewGroup,
            fragmentManager: FragmentManager,
            fragmentTag: String,
            engineEntrypoint: FlutterEngineEntrypoint,
        ): FlutterFragment
        {
            var flutterFragment = fragmentManager.findFragmentByTag(fragmentTag) as FlutterFragment?

            if (flutterFragment == null)
            {
                flutterFragment = FlutterFragment
                    .withCachedEngine(engineEntrypoint.engineId)
                    .shouldAttachEngineToActivity(true)
                    .renderMode(RenderMode.surface)
                    .transparencyMode(TransparencyMode.opaque)
                    .build()

                fragmentManager
                    .beginTransaction()
                    .add(R.id.flutter_fragment_id, flutterFragment, fragmentTag)
                    .commit()
            }

            val flutterContainer = FrameLayout(context)
            root.addView(flutterContainer)
            flutterContainer.id = R.id.flutter_fragment_id
            flutterContainer.layoutParams = LinearLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT,
                1.0f)

            return flutterFragment
        }
    }
}

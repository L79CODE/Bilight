package app.bilight.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.SystemBarStyle
import androidx.activity.viewModels
import app.bilight.android.core.AppModel
import app.bilight.android.ui.MainApp

class MainActivity : ComponentActivity() {
  private val app: AppModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Follow the system appearance so the system bar icons stay readable in both modes.
    val nightMode = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
    val dark = nightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES
    val bars = if (dark) SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
               else SystemBarStyle.light(android.graphics.Color.TRANSPARENT,android.graphics.Color.TRANSPARENT)
    enableEdgeToEdge(statusBarStyle=bars,navigationBarStyle=bars)
    setContent {
      MainApp(app)
    }
  }
  override fun onStart() { super.onStart(); app.retryHistory() }
}

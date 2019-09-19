package david.barbaran.savetheamazon.feature.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import david.barbaran.savetheamazon.R
import david.barbaran.savetheamazon.feature.home.HomeActivity
import david.barbaran.savetheamazon.util.RevealAnimation
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)

        Handler().postDelayed({
            animationView.setMinFrame(40)
        }, 3000)

        helpButton.setOnClickListener {
            animationView.pauseAnimation()
            wavesView.pauseAnimation()

            val v : View = helpButton as View
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "transition")

            val revealX: Int = (v.x + (v.width / 2)).toInt()
            val revealY: Int = (v.y + (v.height / 2)).toInt()

            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX)
            intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY)
            ActivityCompat.startActivity(this, intent, options.toBundle())
            overridePendingTransition(0, 0)
        }
    }

    override fun onResume() {
        super.onResume()
        animationView.playAnimation()
        wavesView.playAnimation()
    }
}
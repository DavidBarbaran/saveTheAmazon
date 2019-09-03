package david.barbaran.savetheamazon

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import david.barbaran.savetheamazon.util.RevealAnimation
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var mRevealAnimation: RevealAnimation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)


        mRevealAnimation = RevealAnimation(homeContent, intent, this)
        mRevealAnimation?.onFinishReveal = object : RevealAnimation.OnFinishReveal {
            override fun onFinishReveal() {
                window.setBackgroundDrawableResource(android.R.color.white)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    window.statusBarColor =
                        ContextCompat.getColor(this@HomeActivity, android.R.color.white)
                } else {
                    window.statusBarColor =
                        ContextCompat.getColor(this@HomeActivity, android.R.color.black)
                }
            }
        }
    }
}

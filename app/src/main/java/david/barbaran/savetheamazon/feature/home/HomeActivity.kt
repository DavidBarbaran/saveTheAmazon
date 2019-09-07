package david.barbaran.savetheamazon.feature.home

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import david.barbaran.savetheamazon.R
import david.barbaran.savetheamazon.feature.home.presenter.HomePresenter
import david.barbaran.savetheamazon.feature.home.presenter.HomeView
import david.barbaran.savetheamazon.model.Donate
import david.barbaran.savetheamazon.util.RevealAnimation
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeView {

    private var mRevealAnimation: RevealAnimation? = null
    //private var homeViewModel: HomeViewModel? = null
    private val donateAdapter = DonateAdapter()

    private var homePresenter : HomePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homePresenter = HomePresenter(this)
        homePresenter?.homeView = this
        setRecycler()
        setWindow()
        initReveal()
    }

    /** Init methods **/

    /*
    private fun setViewModel() {
        homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
        homeViewModel?.apply {
            donateLiveData.observe(this@HomeActivity, getDonateObserver())
        }
    }
     */

    private fun setWindow() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
    }

    private fun setRecycler(){
        donateRecycler.layoutManager = LinearLayoutManager(this)
        donateRecycler.adapter = donateAdapter
    }

    private fun initReveal() {
        mRevealAnimation = RevealAnimation(homeContent, intent, this)
        mRevealAnimation?.onFinishReveal = object : RevealAnimation.OnFinishReveal {
            override fun onFinishReveal() {
                setStatusBar()
                homePresenter?.getDonate()
                // homeViewModel?.getDonate()
            }
        }
    }

    private fun setStatusBar(){
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

    override fun shoDonates(list: MutableList<Donate>) {
        shimmerLayout.hideShimmer()
        shimmerLayout.visibility = View.GONE
        donateAdapter.list = list
        donateAdapter.notifyItemRangeInserted(0, list.size)
    }

    /** Observers **/

    private fun getDonateObserver() = Observer<MutableList<Donate>> {
        shimmerLayout.hideShimmer()
        shimmerLayout.visibility = View.GONE
        donateAdapter.list = it
        donateAdapter.notifyItemRangeInserted(0, it.size)
    }
}

package david.barbaran.savetheamazon.feature.home

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import david.barbaran.savetheamazon.R
import david.barbaran.savetheamazon.feature.home.presenter.HomeView
import david.barbaran.savetheamazon.feature.home.viewmodel.HomeViewModel
import david.barbaran.savetheamazon.model.Donate
import david.barbaran.savetheamazon.util.RevealAnimation
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), HomeView {

    private var mRevealAnimation: RevealAnimation? = null
    private val homeViewModel: HomeViewModel by viewModel()
    private val donateAdapter = DonateAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setRecycler()
        setViewModel()
        setWindow()
        initReveal()
    }

    /** Init methods **/

    private fun setViewModel() {
        homeViewModel.apply {
            donateLiveData.observe(this@HomeActivity, getDonateObserver())
        }
    }

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
                homeViewModel.getDonate()
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
        donateAdapter.notifyDataSetChanged()
    }
}

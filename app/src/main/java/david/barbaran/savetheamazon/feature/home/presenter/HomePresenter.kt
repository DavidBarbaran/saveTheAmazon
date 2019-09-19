package david.barbaran.savetheamazon.feature.home.presenter

import android.content.Context
import david.barbaran.savetheamazon.data.repository.DonateRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomePresenter (context : Context) {

    private val donateRepository = DonateRepositoryImpl(context)
    var homeView : HomeView? = null

    fun getDonate() {
        GlobalScope.launch(Dispatchers.IO) {
            val donates = donateRepository.getDonates()
            GlobalScope.launch(Dispatchers.Main) {
                homeView?.shoDonates(donates)
            }
        }
    }
}
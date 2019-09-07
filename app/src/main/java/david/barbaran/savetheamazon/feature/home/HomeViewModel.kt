package david.barbaran.savetheamazon.feature.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import david.barbaran.savetheamazon.data.repository.DonateRepositoryImpl
import david.barbaran.savetheamazon.model.Donate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    private val donateRepository = DonateRepositoryImpl(context)

    var donateLiveData = MutableLiveData<MutableList<Donate>>()

    fun getDonate() {
        GlobalScope.launch(Dispatchers.IO) {
            val donates = donateRepository.getDonates()
            GlobalScope.launch(Dispatchers.Main) {
                donateLiveData.postValue(donates)
            }
        }
    }
}
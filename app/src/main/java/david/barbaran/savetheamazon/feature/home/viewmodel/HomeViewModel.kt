package david.barbaran.savetheamazon.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import david.barbaran.savetheamazon.data.repository.DonateRepository
import david.barbaran.savetheamazon.model.Donate
import kotlinx.coroutines.*

class HomeViewModel(private val donateRepository: DonateRepository) : ViewModel() {

    var donateLiveData = MutableLiveData<MutableList<Donate>>()

    fun getDonate() {
        CoroutineScope(Dispatchers.IO).launch {
            val donates = donateRepository.getDonates()
            launch(Dispatchers.Main) {
                donateLiveData.postValue(donates)
            }
        }
    }
}
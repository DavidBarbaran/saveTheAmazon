package david.barbaran.savetheamazon.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import david.barbaran.savetheamazon.data.repository.DonateRepository
import david.barbaran.savetheamazon.model.Donate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(private val donateRepository: DonateRepository) : ViewModel() {

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
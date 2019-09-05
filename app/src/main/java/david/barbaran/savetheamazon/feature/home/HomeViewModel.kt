package david.barbaran.savetheamazon.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import david.barbaran.savetheamazon.data.net.RestApi
import david.barbaran.savetheamazon.model.Donate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val restApi = RestApi.instance()
    var donateLiveData = MutableLiveData<MutableList<Donate>>()

    fun getDonate() {
        GlobalScope.launch(Dispatchers.IO) {
            val donate = restApi.getDonate()
            GlobalScope.launch(Dispatchers.Main) {
                donateLiveData.postValue(donate)
            }
        }
    }
}
package david.barbaran.savetheamazon.feature.home.presenter

import david.barbaran.savetheamazon.model.Donate

interface HomeView {
    fun shoDonates(list : MutableList<Donate>)
}
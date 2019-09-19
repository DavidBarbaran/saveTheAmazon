package david.barbaran.savetheamazon.data.repository

import david.barbaran.savetheamazon.model.Donate

interface DonateRepository {
    suspend fun getDonates() : MutableList<Donate>
}
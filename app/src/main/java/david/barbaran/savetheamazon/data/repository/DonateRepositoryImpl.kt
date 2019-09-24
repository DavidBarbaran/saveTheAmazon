package david.barbaran.savetheamazon.data.repository

import android.content.Context
import david.barbaran.savetheamazon.data.database.AppDatabase
import david.barbaran.savetheamazon.data.net.Service
import david.barbaran.savetheamazon.model.Donate
import david.barbaran.savetheamazon.util.NetworkUtil.isOnline

class DonateRepositoryImpl(private val context: Context,
                           private val database: AppDatabase,
                           private val restApi : Service) : DonateRepository {

    override suspend fun getDonates(): MutableList<Donate> {
        val donates: MutableList<Donate>
        if (isOnline(context)) {
            donates = restApi.getDonate()
            database.donateDao().deleteAll()
            database.donateDao().insertAll(donates)
        } else {
            donates = database.donateDao().getDonate()
        }
        return donates
    }
}
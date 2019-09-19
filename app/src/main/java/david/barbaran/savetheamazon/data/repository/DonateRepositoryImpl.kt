package david.barbaran.savetheamazon.data.repository

import android.content.Context
import androidx.room.Room
import david.barbaran.savetheamazon.data.database.AppDatabase
import david.barbaran.savetheamazon.data.net.RestApi
import david.barbaran.savetheamazon.model.Donate
import david.barbaran.savetheamazon.util.NetworkUtil.isOnline

class DonateRepositoryImpl(private val context: Context) : DonateRepository {

    private val database =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()

    private val restApi = RestApi.instance()

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
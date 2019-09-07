package david.barbaran.savetheamazon.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import david.barbaran.savetheamazon.data.database.dao.DonateDao
import david.barbaran.savetheamazon.model.Donate

@Database(entities = [Donate::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "donate_database"
    }

    abstract fun donateDao() : DonateDao
}
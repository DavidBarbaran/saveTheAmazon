package david.barbaran.savetheamazon.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import david.barbaran.savetheamazon.model.Donate

@Dao
interface DonateDao {

    @Query("SELECT * FROM Donate")
    fun getDonate() : MutableList<Donate>

    @Query("DELETE FROM Donate")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: MutableList<Donate>)
}
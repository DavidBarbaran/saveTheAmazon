package david.barbaran.savetheamazon.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Donate (
    @PrimaryKey(autoGenerate = true)
    val id : Int,

    @SerializedName("amount")
    @ColumnInfo(name = "amount")
    var amount : String? = "",

    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description : String? = "",

    @SerializedName("image")
    @ColumnInfo(name = "image")
    var image : String? = ""
)
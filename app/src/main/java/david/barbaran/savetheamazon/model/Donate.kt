package david.barbaran.savetheamazon.model

import com.google.gson.annotations.SerializedName

class Donate {

    @SerializedName("amount")
    var amount : String? = ""

    @SerializedName("description")
    var description : String? = ""

    @SerializedName("image")
    var image : String? = ""
}
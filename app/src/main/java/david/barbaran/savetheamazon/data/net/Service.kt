package david.barbaran.savetheamazon.data.net

import david.barbaran.savetheamazon.model.Donate
import retrofit2.http.GET

interface Service {

    @GET("donate.json")
    suspend fun getDonate(): MutableList<Donate>
}
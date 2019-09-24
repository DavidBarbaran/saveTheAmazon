package david.barbaran.savetheamazon.data.net

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object RestApi {

    private const val TIMEOUT = 30L
    val okHttpClient: OkHttpClient = OkHttpClient
        .Builder()
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()
}
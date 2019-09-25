package david.barbaran.savetheamazon.data.di

import androidx.room.Room
import david.barbaran.savetheamazon.BuildConfig
import david.barbaran.savetheamazon.data.database.AppDatabase
import david.barbaran.savetheamazon.data.net.RestApi
import david.barbaran.savetheamazon.data.net.Service
import david.barbaran.savetheamazon.data.repository.DonateRepository
import david.barbaran.savetheamazon.data.repository.DonateRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    /**
     * AppDatabase singleton
     * @see david.barbaran.savetheamazon.data.database.AppDatabase
     **/

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .build()
    }

    /**
     * Retrofit singleton
     * @see david.barbaran.savetheamazon.data.net.RestApi
     **/

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(RestApi.okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Service::class.java)
    }

    /**
     * DonateRepository singleton
     * @see david.barbaran.savetheamazon.data.repository.DonateRepositoryImpl
     **/

    single<DonateRepository> { DonateRepositoryImpl(androidContext(), get(), get()) }
}